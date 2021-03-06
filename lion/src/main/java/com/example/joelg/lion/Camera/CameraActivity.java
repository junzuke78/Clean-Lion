package com.example.joelg.lion.Camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.*;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.joelg.lion.Gallery.GalleryActivity;
import com.example.joelg.lion.Job.ImgStore;
import com.example.joelg.lion.Job.ImgStoreDao;
import com.example.joelg.lion.Job.JobActivity;
import com.example.joelg.lion.Job.Lion;
import com.example.joelg.lion.R;
import com.example.joelg.lion.db.DaoSession;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.*;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.joelg.lion.R.id.GalleryBtn;

public class CameraActivity extends AppCompatActivity {
    private static final String TAG = "AndroidCameraApi";
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    private static final int REQUEST_CAMERA_PERMISSION = 200;

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    protected CameraDevice cameraDevice;
    protected CameraCaptureSession cameraCaptureSessions;
    protected CaptureRequest.Builder captureRequestBuilder;



    private TextureView textureView;
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {


        @Override
        public void onOpened(CameraDevice camera) {
            Log.e(TAG, "onOpened");
            cameraDevice = camera;
            createCameraPreview();

        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            Log.e(TAG, "On Disconnected");
            // cameraDevice.close();
            // stopBackgroundThread();


        }

        @Override
        public void onError(CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice = null;
        }


    };

    private ImageButton takePictureButton;
    private Size imageDimension;
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    };
    //save file

    private Handler mBackgroundHandler = new Handler();
    private HandlerThread mBackgroundThread;
    private String cameraId ;
    private int CameraWidth = 640;
    private int CameraHeight = 480;


    private File mediaFile;
    private ImgStore GallerySave;
    private String timeStamp;

    public void run() {
        System.out.println("Thread Running");
    }

    private Long ImgID() {
        long upperLimit = 15L;
        long lowerLimit = 0L;
        long GeneratedID;

        GeneratedID = lowerLimit + ( long ) (Math.random() * upperLimit - lowerLimit);
        return GeneratedID;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_camera);
        textureView = findViewById(R.id.texture);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);
        takePictureButton = findViewById(R.id.CaptureBtn);

        assert takePictureButton != null;

        ///##############################################

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePicture();
            }
        });


        //################################################
        ImageButton BackButton = findViewById(R.id.BackCamBtn);
        BackButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                {

                    Intent intent = new Intent(CameraActivity.this, JobActivity.class);
                    startActivity(intent);


                }
            }
        });



        //#######################################
         ImageButton GalleryButton = findViewById(GalleryBtn);
        GalleryButton.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v) {
                {

                    Intent intent = new Intent(CameraActivity.this, GalleryActivity.class);
                    startActivity(intent);

                }
            }

        });
    }


    protected void takePicture() {
        if (null == cameraDevice) {
            Log.e(TAG, "cameraDevice is null");
            return;
        }
        CameraManager manager = ( CameraManager ) getSystemService(Context.CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes = null;
            if (characteristics != null) {
                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                        .getOutputSizes(ImageFormat.JPEG);
            }
            int width = CameraWidth;
            int height = CameraHeight;
            if (jpegSizes != null && 0 < jpegSizes.length) {
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();
            }
            final ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 10);

            List<Surface> outputSurface = new ArrayList<>(10);
            outputSurface.add(reader.getSurface());
            outputSurface.add(new Surface(textureView.getSurfaceTexture()));

            final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);



            //check oreintation
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));


            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {


                @Override
                public void onImageAvailable(ImageReader imageReader) {
                    Image image = null;
                    try {
                        image = reader.acquireLatestImage();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];
                        buffer.get(bytes);
                        save(bytes);

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        {
                            if (image != null)
                                image.close();

                        }
                    }
                }

                private void save(byte[] bytes) throws IOException
                {
                    File imgfile = getOutputMediaFile();
                    OutputStream outputStream = null;

                    try {
                        outputStream = new FileOutputStream(imgfile);
                        outputStream.write(bytes);
                    } finally {
                        DBsave();
                        if (outputStream != null)

                            outputStream.close();

                    }
                }

            };

            reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
            final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                   // Toast.makeText(CameraActivity.this, "Saved" + fileNew, Toast.LENGTH_SHORT).show();


                    createCameraPreview();

                }
            };
            cameraDevice.createCaptureSession(outputSurface, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NotNull CameraCaptureSession cameraCaptureSession) {
                    try {
                        cameraCaptureSession.capture(captureBuilder.build(), captureListener, mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NotNull CameraCaptureSession cameraCaptureSession) {

                }
            }, mBackgroundHandler);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    private void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();

            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    if (cameraDevice == null)
                        return;
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(CameraActivity.this, "Changed", Toast.LENGTH_SHORT).show();
                }
            }, null);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void updatePreview() {
        if (cameraDevice == null)
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO);
        try {
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    private void openCamera() {
        CameraManager manager = ( CameraManager ) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = manager.getCameraIdList()[0];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResullts) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResullts[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "you cant use camera without permission", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    protected void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());

    }



    @Override
    protected void onResume() {
        super.onResume();
        if (textureView.isAvailable())
            openCamera();
        else
            textureView.setSurfaceTextureListener(textureListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeCamera();

    }

    private void closeCamera() {
        Log.e(TAG, "Closing Camera");

        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
            stopBackgroundThread();

        }

    }


    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {

            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private File getOutputMediaFile() {
       File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),"Lion");
       if(!mediaStorageDir.exists()) {
           if(!mediaStorageDir.mkdirs()){
               Log.d("Lion","failed to create Dir");
               return null;
           }
       }
        timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
    mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_ "+ timeStamp + ".jpg");
    return mediaFile;
}

    private void DBsave() {
        DaoSession daoSession = ((Lion) getApplicationContext()).getDaoSession();
        ImgStoreDao GallerySave = daoSession.getImgStoreDao();
        ImgStore i = GallerySave.load(ImgID());
        String tmp = mediaFile.toString();
        i.setImgURL(tmp);
        i.setImgTimeStamp(timeStamp);


    }
}

















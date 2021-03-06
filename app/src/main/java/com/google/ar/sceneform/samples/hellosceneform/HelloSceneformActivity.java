/*
 * Copyright 2018 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.ar.sceneform.samples.hellosceneform;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.samples.hellosceneform.model.Battlefield;
import com.google.ar.sceneform.samples.hellosceneform.model.ConnectionChannel;
import com.google.ar.sceneform.samples.hellosceneform.model.Message;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.gson.Gson;

import java.util.Locale;

/**
 * This is an example activity that uses the Sceneform UX package to make common AR tasks easier.
 */
public class HelloSceneformActivity extends AppCompatActivity {
    private static final String TAG = HelloSceneformActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    private ArFragment arFragment;
    private ModelRenderable blank, nothing, yep;
    private Battlefield myField = new Battlefield();

    boolean existCube = false;
    Node[][] nodeArray;
    Node[][] enemyNode;

    Button buttonConnect;
    Button buttonSend;

    SwitchCompat deviceRole; // true - master, false - slave

    ConnectionChannel connectionChannel;
    final String deviceName = String.format(Locale.CANADA, "Device %d", (int)(Math.random() * 1000));

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    // CompletableFuture requires api level 24
    // FutureReturnValueIgnored is not valid
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ux);

//        if (!checkIsSupportedDeviceOrFinish(this)) {
//            return;
//        }

        connectionChannel = new ConnectionChannel(this, getPackageName(), deviceName);

        buttonConnect = findViewById(R.id.button2);
        buttonConnect.setOnClickListener(view -> {
            if (deviceRole.isChecked()){
                connectionChannel.advertise();
            }else {
                connectionChannel.discover();
            }
        });

        buttonSend = findViewById(R.id.button3);
        buttonSend.setOnClickListener(view -> {
            String message = "Send value " +  Math.random();
            connectionChannel.sendMessage(new Gson().toJson(new Message(message)), deviceName);
        });

//        nodeArray = new Node[10][10];
//        enemyNode = new Node[10][10];


      //  arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);


        // When you build a Renderable, Sceneform loads its resources in the background while returning
        // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().
//        ModelRenderable.builder()
//                .setSource(this, R.raw.blank2)
//                .build()
//                .thenAccept(renderable -> blank = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
//        ModelRenderable.builder()
//                .setSource(this, R.raw.nothing)
//                .build()
//                .thenAccept(renderable -> nothing = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
//        ModelRenderable.builder()
//                .setSource(this, R.raw.tugboat)
//                .build()
//                .thenAccept(renderable -> yep = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });

        deviceRole = findViewById(R.id.switch1);



//        arFragment.setOnTapArPlaneListener(
//                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
//                    if (existCube)
//                        return;
//                    //Battlefield.OneShip oneShip = new Battlefield.OneShip(new Point(1, 1), new Point(1, 1));
//
//
//                    //Toast.makeText(HelloSceneformActivity.this, "!!!!!!", Toast.LENGTH_SHORT).show();
//                    Anchor anchor = hitResult.createAnchor();
//                    AnchorNode anchorNode = new AnchorNode(anchor);
//                    anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//                    Node baseNode = new Node();
//                    anchorNode.addChild(baseNode);
//                    baseNode.setWorldScale(new Vector3(.02f, .025f, .02f));
////                baseNode.setLocalPosition(new Vector3(0.2f, 0.001f, .2f));
//
//                    float count = 9;
//                    float offset = 1.7f;
//                    for (int y = 0; y < 2; y++) {
//                        //float step = 0.01f / count;
//                        for (int i = 0; i < count; i++) {
//                            for (int j = 0; j < count; j++) {
//                                //   float offset = (step - 0.28f);
//                                addObject(baseNode, offset * j,
//                                        offset * i, myField, j, i, y);
//
//                            }
//                        }
//
//                    }
//                    existCube = true;
//
//                });
    }


    public void addObject(Node baseNode, float xOffset, float zOffset, Battlefield mine, int xpos, int ypos, int y) {
        Node node = new Node();
        node.setParent(baseNode);
        node.setRenderable(blank);
        if (y == 0)
            node.setLocalPosition(new Vector3(xOffset, 0.0f, zOffset));
        else
            node.setLocalPosition(new Vector3(xOffset + 19f, 0.0f, zOffset));
        node.setOnTapListener((hitTestResult, motionEvent) -> {
                    Point shipCoord = new Point(xpos, ypos);

                    if (!myField.isPlaced(shipCoord, shipCoord)) {
                        node.setRenderable(yep);
                        myField.addShip(new Battlefield.OneShip(shipCoord, shipCoord));
                    }
                }
        );
        if (y == 0)
            nodeArray[xpos][ypos] = node;
        else
            enemyNode[xpos][ypos] = node;
    }


    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     * <p>
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     * <p>
     * <p>Finishes the activity if Sceneform can not run
     */
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }
}

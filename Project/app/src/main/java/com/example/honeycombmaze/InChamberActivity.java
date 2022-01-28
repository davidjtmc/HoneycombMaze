package com.example.honeycombmaze;

import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class InChamberActivity extends AppCompatActivity {

    NodeMap nodeMap;

    ImageView tButton;
    ImageView trButton;
    ImageView brButton;
    ImageView bButton;
    ImageView blButton;
    ImageView tlButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_chamber);

        InputStream in_s = getCSVRes();
        nodeMap = new NodeMap(in_s);

        tButton = findViewById(R.id.tButton);
        trButton = findViewById(R.id.trButton);
        brButton = findViewById(R.id.brButton);
        bButton = findViewById(R.id.bButton);
        blButton = findViewById(R.id.blButton);
        tlButton = findViewById(R.id.tlButton);


        tButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ChangeNode(1);
            }
        });

        trButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ChangeNode(2);
            }
        });

        brButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ChangeNode(3);
            }
        });

        bButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ChangeNode(4);
            }
        });

        blButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ChangeNode(5);
            }
        });

        tlButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ChangeNode(6);
            }
        });

        ChooseButtons();
    }

    protected void ChangeNode(int decision){
        nodeMap.decision(decision);
        CheckStatus(nodeMap.GetCurrentNode().getID());
        ChooseButtons();
    }

    public void ChooseButtons(){
        HideDirButtons();

        if (nodeMap.GetCurrentNode().gettID() != -1){
            ShowButton(tButton);
        }
        if (nodeMap.GetCurrentNode().getTrID() != -1){
            ShowButton(trButton);
        }
        if (nodeMap.GetCurrentNode().getBrID() != -1){
            ShowButton(brButton);
        }
        if (nodeMap.GetCurrentNode().getbID() != -1){
            ShowButton(bButton);
        }
        if (nodeMap.GetCurrentNode().getBlID() != -1){
            ShowButton(blButton);
        }
        if (nodeMap.GetCurrentNode().getTlID() != -1){
            ShowButton(tlButton);
        }
    }

    protected void CheckStatus(int nodeID){
        switch(nodeID){
            case 102:
                Intent toLossScreen = new Intent(InChamberActivity.this, FallenOutScreen.class);
                startActivity(toLossScreen);
                break;
            case 101:
                Intent toSuccessScreen = new Intent(InChamberActivity.this, SuccessfulRun.class);
                startActivity(toSuccessScreen);
                break;
        }
    }

    protected InputStream getCSVRes(){
        Resources res = getResources();
        return res.openRawResource(R.raw.backend);
    }

    public void ShowButton(ImageView button){
        button.setVisibility(View.VISIBLE);

    }

    public void GoneButton(ImageView button) {button.setVisibility(View.GONE);}

    public void HideButton(ImageView button){
        button.setVisibility(View.INVISIBLE);
    }

    public void HideDirButtons(){
        HideButton(tButton);
        HideButton(trButton);
        HideButton(brButton);
        HideButton(bButton);
        HideButton(blButton);
        HideButton(tlButton);
    }

    public void ShowDirButtons(){
        ShowButton(tButton);
        ShowButton(trButton);
        ShowButton(brButton);
        ShowButton(bButton);
        ShowButton(blButton);
        ShowButton(tlButton);
    }



    protected void BeeAnimation(int direction){
        ImageView bee = (ImageView) findViewById(R.id.bee);
        float xPos = bee.getX();
        float yPos = bee.getY();

       /*GoneButton((ImageView) findViewById(R.id.tButton));
        GoneButton((ImageView) findViewById(R.id.trButton));
        GoneButton((ImageView) findViewById(R.id.brButton));
        GoneButton((ImageView) findViewById(R.id.bButton));
        GoneButton((ImageView) findViewById(R.id.blButton));
        GoneButton((ImageView) findViewById(R.id.tlButton));*/


        switch(direction){
            case 1:
                bee.setRotation(bee.getRotation()+90);
            case 2:
                bee.setRotation(135);
            case 3:
                bee.setRotation(180);
            case 4:
                bee.setRotation(-135);
            case 5:
                bee.setRotation(-45);
        }

    }
}
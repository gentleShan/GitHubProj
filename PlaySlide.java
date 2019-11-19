package FX;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class PlaySlide extends Application {
    private int idx = 0;
    public void start(Stage primaryStage){
        final ArrayList<Image> imageList = new ArrayList<>();
        imageList.add(new Image("image\\1.png",800,600,false,false));
        imageList.add(new Image("image\\2.png",800,600,false,false));
        imageList.add(new Image("image\\3.png",800,600,false,false));
        imageList.add(new Image("image\\4.png",800,600,false,false));
        imageList.add(new Image("image\\5.png",800,600,false,false));
        ImageView imageView = new ImageView();
        StackPane pane = new StackPane(imageView);
        pane.setPadding(new Insets(12,12,12,12));
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        // 是否还原为初始状态（如果设置为true，会在执行次数内还会切换，至于这个初始状态什么意思自己体会吧）
        timeline.setAutoReverse(true);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                imageView.setImage(imageList.get(idx++));
                if (idx >= imageList.size()) {
                    idx = 0;
                }
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        imageView.setOnMouseClicked(event -> {
            if(timeline.getStatus() == Animation.Status.PAUSED)
                timeline.play();
            else
            {
                timeline.pause();

            }
        });
        Scene scene = new Scene(pane, 820, 620);
        primaryStage.setScene(scene);
        primaryStage.setTitle("幻灯片播放");
        primaryStage.show();

    }
}

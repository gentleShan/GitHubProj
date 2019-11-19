package FX;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaFXText extends Application {
    private int i = 0;
    public void start(Stage primary) throws Exception{
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide0.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide1.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide2.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide3.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide4.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide5.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide6.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide7.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide8.txt"));
        files.add(new File("D:\\java_work\\helloidea\\src\\text\\slide9.txt"));
        Scanner[] input = new Scanner[10];
        String[] s = new String[10];
        String t = "";
        for(int i = 0; i<10; i++ ){
            input[i] = new Scanner(files.get(i));
            s[i] = input[i].nextLine();
            while (input[i].hasNext()){
                t = input[i].nextLine();
                s[i] += "\n"+ t;
            }
            input[i].close();
            t = "";
        }
        //System.out.println(s[3]);

        TextArea textArea=new TextArea();
        textArea.setWrapText(true);
        textArea.setDisable(true);
        //textArea.setStyle("-fx-text-fill:#d1abff");
        textArea.setStyle("-fx-font-size:15");

        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(20);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        // 是否还原为初始状态（如果设置为true，会在执行次数内还会切换，至于这个初始状态什么意思自己体会吧）
        timeline.setAutoReverse(true);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {

                    if (i >= 10) {
                        i = 0;
                    }
                    textArea.setText(s[i++]);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        textArea.setOnMouseClicked(event -> {
            if(timeline.getStatus() == Animation.Status.PAUSED)
                timeline.play();
            else
            {
                timeline.pause();
            }
        });
        StackPane pane = new StackPane(textArea);
        Scene scene = new Scene(pane);
        primary.setScene(scene);
        primary.setTitle("文本幻灯片");
        primary.show();
    }
    public static void main(String[] agrs) {
        launch(agrs);
    }
}
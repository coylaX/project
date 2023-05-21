package view;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import static view.MusicPlayer.*;
import javax.swing.event.ChangeListener;

public class VolumeFrame extends JFrame {
    public VolumeFrame(){
        setVolumeSlider();
        setSize(500,200);
        setTitle("音量调节");
    }
    private void setVolumeSlider(){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        JSlider volumeSlider=new JSlider(0,100);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setVisible(true);
        volumeSlider.setLocation(0,0);
        volumeSlider.setSize(400,300);
        add(volumeSlider);
        volumeSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                //long dbValue=gainControl.getValue();//获取原音量值 ,你要获取所有的话，自己写循环，每次循环都把样本音量放到集合或者数组去
                gainControl.setValue((float) (80*Math.log10(volumeSlider.getValue()*0.4+10)-130)); // -10.0f就是减少10分贝的意思，自己看需要调整
            }
        });
    }
}

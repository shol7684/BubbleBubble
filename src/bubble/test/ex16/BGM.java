package bubble.test.ex16;

import java.io.File;
import java.io.IOException;

import javax.script.AbstractScriptEngine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGM {

	public BGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			gainControl.setValue(-30.0f);
			
			clip.start();
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package boundary;

import java.io.FileNotFoundException;

import javafx.scene.layout.Pane;

public interface ScreenStrategy {
	
	Pane generateScreenStrategy() throws FileNotFoundException;

}

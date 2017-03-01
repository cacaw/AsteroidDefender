package View;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpaceGif extends JPanel {
	
	private Icon icon = new ImageIcon("Images/spacebackgroundGIF.gif");

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	}
	
	


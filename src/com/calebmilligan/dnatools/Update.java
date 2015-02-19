package com.calebmilligan.dnatools;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

public class Update implements Runnable {
	private DNA_Tools window;
	private boolean doDisplay;
	private final String drive = "https://www.dropbox.com/s/649cl6pnd8n41md/Current%20Version.txt?dl=1";

	public Update(DNA_Tools window, boolean doDisplay) {
		this.window = window;
		this.doDisplay = doDisplay;
	}

	public void run() {
		if(doDisplay) {
			window.updatePane.setVisible(true);
			window.lblStart.setVisible(false);
		}
		double current = 0.0;
		double latest = 0.0;
		try {
			if(doDisplay) {
				window.updatePane.setText("Connecting to server...\n");
			}
			current = Double.parseDouble(window.version);
			latest = Double.parseDouble(checkUpdates());
		}
		catch(IOException e) {
			if(doDisplay) {
				window.updatePane.setForeground(new Color(255, 0, 0));
				window.updatePane.setText(window.updatePane.getText() + "Connection failed: "
						+ e.getClass().getName() + "\n");
			}
			return;
		}
		if(doDisplay) {
			window.updatePane.setText(window.updatePane.getText() + "Connected\n");
		}
		if(current < latest) {
			window.frmDnaTools.setTitle("DNA Tools v" + window.version + " | A new update is available");
			if(doDisplay) {
				window.updatePane.setText(window.updatePane.getText() + "New update found: v" + latest + "\n");
				window.updatePane.setText(window.updatePane.getText() + "Downloading updates...\n");
				try {
					download("https://www.dropbox.com/s/wvhk5z0visqw33y/config.yml?dl=1", "config.yml");
					download("https://www.dropbox.com/s/1illqi2wqg3wi4n/DNA%20Tools.jar?dl=1", "DNA Tools.jar");
					download("https://www.dropbox.com/s/8i0p49tw52g4t5b/run.bat?dl=1", "run.bat");
				}
				catch(Exception e) {
					if(doDisplay) {
						window.updatePane.setForeground(new Color(255, 0, 0));
						window.updatePane.setText(window.updatePane.getText() + "Download failed: "
								+ e.getClass().getName());
					}
					return;
				}
				window.updatePane.setText(window.updatePane.getText() + "Download success\n");
			}
			try {
				if(doDisplay) {
					window.updatePane.setText(window.updatePane.getText() + "Restarting DNA Tools...\n");
					try {
						Thread.sleep(500);
					}
					catch(InterruptedException e) {

					}
					//window.frmDnaTools.dispose();
					Runtime.getRuntime().exec("cmd /c start javaw -jar \"DNA Tools.jar\"");
					System.exit(0);
				}
			}
			catch(Exception e) {
			}
			return;
		}
		else {
			if(doDisplay) {
				window.updatePane.setText(window.updatePane.getText() + "No new updates found\n");
			}
		}
	}

	private String checkUpdates() throws IOException {
		URL url = new URL(drive);
		Scanner scan = new Scanner(url.openStream());
		String line = "";
		while(scan.hasNext()) {
			line += scan.next() + "\n";
		}
		scan.close();
		return line;
	}

	private void download(String url, String file) throws IOException {
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(file);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
	}
}

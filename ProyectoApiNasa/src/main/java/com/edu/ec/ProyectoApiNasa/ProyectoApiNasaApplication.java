package com.edu.ec.ProyectoApiNasa;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import view.MarsPhotoViewerFrame;

import javax.swing.*;

@SpringBootApplication
public class ProyectoApiNasaApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				new MarsPhotoViewerFrame();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}



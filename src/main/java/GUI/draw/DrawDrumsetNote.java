package GUI.draw;

import javafx.fxml.FXML;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Box;
import javafx.scene.shape.CubicCurve;

import models.measure.note.Note;
import models.measure.Measure;

public class DrawDrumsetNote {

	private double top;
	private double startX;
	private double startY;
	private Note note;
	@FXML private Pane pane;

	public DrawDrumsetNote(Pane pane, Note note, double top, double startX, double startY) {
		super();
		this.note = note;
		this.startX = startX;
		this.top = top - 30;
		this.startY = startY;
		this.pane = pane;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public void drawX() {
		// Center coordinates of the "x"
		double xCenterX = getStartX() + 3;
		double xCenterY = getStartY() - 3;

		// Draw to crossing line to make the "x"
		Line topLeftToBottomRight = new Line(xCenterX-4, xCenterY+4, xCenterX+4, xCenterY-4);
		Line topRightToBottomLeft = new Line(xCenterX+4, xCenterY+4, xCenterX-4, xCenterY-4);

		if (note.getType().equals("half") || note.getType().equals("whole")) {
			// If the note is a half note or a whole note, then we draw the an outline of the "x".
			// We do this by drawing a black "x" and adding a smaller white "x" on top of it.

			// Make the black "x" wider and add it to the screen
			topLeftToBottomRight.setStrokeWidth(3.5);
			topRightToBottomLeft.setStrokeWidth(3.5);
	    	pane.getChildren().add(topLeftToBottomRight);
	    	pane.getChildren().add(topRightToBottomLeft);

	    	// Create and add the  white "x"
			topLeftToBottomRight = new Line(xCenterX-4, xCenterY+4, xCenterX+4, xCenterY-4);
			topRightToBottomLeft = new Line(xCenterX+4, xCenterY+4, xCenterX-4, xCenterY-4);
			topLeftToBottomRight.setStroke(Color.WHITE);
			topRightToBottomLeft.setStroke(Color.WHITE);
			topLeftToBottomRight.setStrokeWidth(1.5);
			topRightToBottomLeft.setStrokeWidth(1.5);
	    	pane.getChildren().add(topLeftToBottomRight);
	    	pane.getChildren().add(topRightToBottomLeft);
		} else {
			// If the note is not a half note or a whole note, then just draw the "x"
			topLeftToBottomRight.setStrokeWidth(1.5);
			topRightToBottomLeft.setStrokeWidth(1.5);
	    	pane.getChildren().add(topLeftToBottomRight);
	    	pane.getChildren().add(topRightToBottomLeft);
		}

		// Only draw the stem if the note is not a whole note
		if (!note.getType().equals("whole")) {
			// Drawing the stem
			Line stem = new Line(getStartX()+8, getStartY()-8, getStartX()+8, this.top);
			stem.setStrokeWidth(1.5);
	    	pane.getChildren().add(stem);
		}

    	Blend blend = new Blend(); 
		ColorInput topInput = new ColorInput(getStartX()-3, getStartY()-7, 14, 8, Color.WHITE); 
		blend.setTopInput(topInput); 
		blend.setMode(BlendMode.OVERLAY);
	}

	public void drawO() {
		// The note is drawn with an ellipse
		Ellipse ellipse;
		if (!note.getType().equals("whole")) {
			// If the not is not a whole not, then it is rotated slightly
			ellipse = new Ellipse(getStartX()+3, getStartY()-2, 6.0, 4.5);
			ellipse.setRotate(330);
		} else {
			// Whole notes are horizontal
			ellipse = new Ellipse(getStartX()+3, getStartY()-3, 6.0, 5.0);
		}
		ellipse.toFront();

		Blend blend = new Blend();
		ColorInput topInput = new ColorInput(getStartX()-3, getStartY()-7, 14, 8, Color.WHITE);
		blend.setTopInput(topInput);
		blend.setMode(BlendMode.OVERLAY);

    	pane.getChildren().add(ellipse);
    	ellipse.setEffect(blend);

    	if (note.getType().equals("half")) {
    		// If the note is a half note, then the note is an outline of the note.
    		// We do this by drawing a black ellipse and a smaller white ellipse on top of it.
    		// The black ellipse was already drawn, so we just need to draw the white ellipse.

    		// Draw white ellipse to screen
    		ellipse = new Ellipse(getStartX()+3, getStartY()-2, 4.5, 3.0);
    		ellipse.setFill(Color.WHITE);
    		ellipse.setRotate(330);
    		ellipse.toFront();
        	pane.getChildren().add(ellipse);

        	// Half notes have stems, so draw stem
    		Line stem = new Line(getStartX()+8, getStartY()-5, getStartX()+8, this.top);
    		stem.setStrokeWidth(1.5);
        	pane.getChildren().add(stem);
    	} else if (note.getType().equals("whole")) {
    		// Whole notes are an outline of the note, but they are outlined differently than half notes.
    		// The black ellipse was already drawn, so we just draw a white ellipse on top of it to give it an outlines effect.

    		// Draw white ellipse to the screen
    		ellipse = new Ellipse(getStartX()+3, getStartY()-3, 2.5, 4.0);
    		ellipse.setFill(Color.WHITE);
    		ellipse.setRotate(345);
    		ellipse.toFront();
        	pane.getChildren().add(ellipse);
    	} else {
    		// All other notes have stems, so draw stem
    		Line stem = new Line(getStartX()+8, getStartY()-5, getStartX()+8, this.top);
    		stem.setStrokeWidth(1.5);
        	pane.getChildren().add(stem);
    	}

	}

	public void drawConnection() {
		if (note.getType().equals("eighth")) {
			Line connection = new Line(getStartX()+8, this.top, getStartX()+58, this.top);
			connection.setStrokeWidth(3);
			pane.getChildren().add(connection);
		} else if (note.getType().equals("16th")) {
			Line connection = new Line(getStartX()+8, this.top, getStartX()+58, this.top);
			connection.setStrokeWidth(3);
			pane.getChildren().add(connection);

			connection = new Line(getStartX()+8, this.top+5, getStartX()+58, this.top+5);
			connection.setStrokeWidth(3);
			pane.getChildren().add(connection);
		}
	}

	public void drawFlag() {
		if (note.getType().equals("eighth")) {
			CubicCurve cubicCurve = new CubicCurve(
				getStartX()+8, getStartY()-35,
				getStartX()+8, getStartY()-30,
				getStartX()+20, getStartY()-25,
				getStartX()+18, getStartY()-15
			);
	    	pane.getChildren().add(cubicCurve);
		} else if (note.getType().equals("16th")) {
			CubicCurve cubicCurve1 = new CubicCurve(
				getStartX()+8, getStartY()-35,
				getStartX()+8, getStartY()-30,
				getStartX()+20, getStartY()-25,
				getStartX()+18, getStartY()-15
			);
	    	pane.getChildren().add(cubicCurve1);

			CubicCurve cubicCurve2 = new CubicCurve(
				getStartX()+8, getStartY()-30,
				getStartX()+8, getStartY()-25,
				getStartX()+20, getStartY()-20,
				getStartX()+18, getStartY()-10
			);
	    	pane.getChildren().add(cubicCurve2);
		}
	}

	public void draw() {
		// If the note is octave 5 and step A, then it is above the staff and a small line must be drawn behind it.
		if (note.getUnpitched().getDisplayOctave() == 5 && note.getUnpitched().getDisplayStep().equals("A")) {
			pane.getChildren().add(new Line(getStartX()-5, getStartY()-3, getStartX()+11, getStartY()-3));
		}

		// If note head exists and is an x, then draw "x", otherwise draw "o"
		if (note.getNotehead() != null && note.getNotehead().getType().equals("x")) {
			this.drawX();
		}
		else {
			this.drawO();
		}
	}

	public void drawDrumClef1() {

		Rectangle r1 = new Rectangle();
		r1.setWidth(3);
		r1.setHeight(20);
		r1.setTranslateX(5);
		r1.setTranslateY(20);
		pane.getChildren().add(r1);
		
	}

	public void drawDrumClef2() {

		Rectangle r1 = new Rectangle();
		r1.setWidth(3);
		r1.setHeight(20);
		r1.setTranslateX(10);
		r1.setTranslateY(20);
		pane.getChildren().add(r1);
		
	}
}

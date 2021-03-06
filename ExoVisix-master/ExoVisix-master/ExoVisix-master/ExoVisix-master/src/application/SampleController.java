package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class SampleController {

	// **********************************************************************************************
	// Mention The file location path where the face will be saved & retrieved

	public String filePath = "./faces";

	// **********************************************************************************************
	@FXML
	private Button startCam;
	@FXML
	private Button stopBtn;

	@FXML
	private Button saveBtn;

	@FXML
	private Button recogniseBtn;
	@FXML
	private Button stopRecBtn;
	@FXML
	private ImageView frame;
	@FXML
	private ImageView motionView;
	@FXML
	private AnchorPane pdPane;
	@FXML
	private TitledPane dataPane;
	@FXML
	private TextField fullName;
	@FXML
	private TextField className;
	@FXML
	private TextField code;

	@FXML
	public ListView<String> logList;
	@FXML
	public ListView<String> output;
	@FXML
	public ProgressIndicator pb;
	@FXML
	public Label savedLabel;
	@FXML
	public Label warning;
	@FXML
	public Label title;
	@FXML
	public TilePane tile;
	@FXML
	public TextFlow ocr;
	@FXML
	public Label time;
//**********************************************************************************************
	FaceDetector faceDetect = new FaceDetector(); // Creating Face detector object
	Database database = new Database(); // Creating Database object

	ArrayList<String> user = new ArrayList<String>();
	ImageView imageView1;

	public static ObservableList<String> event = FXCollections.observableArrayList();
	public static ObservableList<String> outEvent = FXCollections.observableArrayList();

	public boolean enabled = false;
	public boolean isDBready = false;

	// **********************************************************************************************
	public void putOnLog(String data) {

	

	}

	@FXML
	protected void startCamera() throws SQLException {

		new Thread(() -> {
			javafx.application.Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
						LocalTime currentTime = LocalTime.now();
						time.setText(
								currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
					}), new KeyFrame(Duration.seconds(1)));
					clock.setCycleCount(Animation.INDEFINITE);
					clock.play();
				}
			});

		}).start();

		// *******************************************************************************************
		// initializing objects from start camera button event
		faceDetect.init();

		faceDetect.setFrame(frame);

		faceDetect.start();

		if (!database.init()) {

			putOnLog("Error: Database Connection Failed ! ");

		} else {
			isDBready = true;
			putOnLog("Success: Database Connection Succesful ! ");
		}

		// *******************************************************************************************
		// Activating other buttons
		startCam.setVisible(false);

		stopBtn.setVisible(true);
		saveBtn.setDisable(false);

		if (isDBready) {
			recogniseBtn.setDisable(false);
		}

		dataPane.setDisable(false);
		// shapeBtn.setDisable(false);

		if (stopRecBtn.isDisable()) {
			stopRecBtn.setDisable(false);
		}
		// *******************************************************************************************

		tile.setPadding(new Insets(15, 15, 55, 15));
		tile.setHgap(30);

		// **********************************************************************************************
		// Picture Gallary

		String path = filePath;

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		// Image reader from the mentioned folder
		for (final File file : listOfFiles) {

			imageView1 = createImageView(file);
			tile.getChildren().addAll(imageView1);
		}
		putOnLog(" Real Time WebCam Stream Started !");

		// **********************************************************************************************
	}

	int count = 0;
	private void exportTxt(String mssv, String name, String className, String timeCheck, String dates)
			throws Exception {

		File file = new File("D:\\test\\test.txt");
		if (!file.exists()) {
			FileWriter FW = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(FW);
			String header = "MSSV" + "\t\t";
			header += "NAME" + "\t\t";
			header += "CLASS" + "\t";
			header += "THOI GIAN" + "\t";
			header += "NGAY THANG" ;
			
			bw.write(header);
			bw.write("\n");
			bw.write(mssv);
			bw.write("\t");
			bw.write(name);
			bw.write("\t");
			bw.write(className);
			bw.write("\t");
			bw.write(timeCheck);
			bw.write("\t");
			bw.write(dates);
			bw.write("\n");
			bw.flush();
		} else {
			FileWriter FW = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(FW);
			bw.write(mssv);
			bw.write("\t");
			bw.write(name);
			bw.write("\t");
			bw.write(className);
			bw.write("\t");
			bw.write(timeCheck);
			bw.write("\t");
			bw.write(dates);
			bw.write("\n");
			bw.flush();
		}

	}



	@FXML
	protected void faceRecognise() throws Exception {

		faceDetect.setIsRecFace(true);
		// printOutput(faceDetect.getOutput());
	
		// Getting detected faces
		user = faceDetect.getOutput();

		if (count > 0) {
			String logs = user.get(0) + ": " + user.get(1);
			event.add(logs);
			logList.setItems(event);

			// Retrieved data will be shown in Fetched Data pane
			String intro = "********* Thông tin : " + user.get(1) + " *********";
			outEvent.add(intro);

			String codeSt = "Mã số sinh viên: \t\t:\t" + user.get(0);
			outEvent.add(codeSt);

			output.setItems(outEvent);
			String fullName = "Họ và tên: \t\t:\t" + user.get(1);
			outEvent.add(fullName);
			output.setItems(outEvent);

			String className = "Mã lớp:  \t\t:\t\t" + user.get(2);
			outEvent.add(className);
			output.setItems(outEvent);

			String timeCheck = java.time.LocalTime.now().toString();
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     	String dates = date.format(formatter);
			exportTxt(user.get(0), user.get(1), user.get(2), timeCheck, dates);

		}
		count++;
		
		stopRecBtn.setDisable(false);

	}

	@FXML
	protected void stopRecognise() {

		faceDetect.setIsRecFace(false);
		faceDetect.clearOutput();

		this.user.clear();

		recogniseBtn.setText("Recognise Face");

		stopRecBtn.setDisable(true);

		putOnLog("Face Recognition Deactivated !");

	}

	@FXML
	protected void startMotion() {

		faceDetect.setMotion(true);
		putOnLog("motion Detector Activated !");

	}

	@FXML
	protected void saveFace() throws SQLException {

		// Input Validation

		if (fullName.getText().trim().isEmpty() || className.getText().trim().isEmpty()
				|| code.getText().trim().isEmpty()) {
			new Thread(() -> {

				try {
					warning.setVisible(true);

					Thread.sleep(2000);

					warning.setVisible(false);

				} catch (InterruptedException ex) {
				}

			}).start();

		} else {
			// Progressbar
			pb.setVisible(true);

			savedLabel.setVisible(true);

			new Thread(() -> {

				try {

					faceDetect.setFullName(fullName.getText());
					faceDetect.setClassName(className.getText());
					faceDetect.setCode(Integer.parseInt(code.getText()));

					database.setFullName(fullName.getText());
					database.setClassName(className.getText());
					database.setCode(Integer.parseInt(code.getText()));
					if (database.getCode(Integer.parseInt(code.getText())) == 0) {
						database.insert();
					}

					javafx.application.Platform.runLater(new Runnable() {

						@Override
						public void run() {
							pb.setProgress(100);
						}
					});
					savedLabel.setVisible(true);
					Thread.sleep(2000);

					javafx.application.Platform.runLater(new Runnable() {

						@Override
						public void run() {
							pb.setVisible(false);
						}
					});
					javafx.application.Platform.runLater(new Runnable() {

						@Override
						public void run() {
							savedLabel.setVisible(false);
						}
					});

				} catch (InterruptedException ex) {
				}

			}).start();

			faceDetect.setSaveFace(true);

		}
	}

	@FXML
	protected void stopCam() throws SQLException {

		faceDetect.stop();

		startCam.setVisible(true);
		stopBtn.setVisible(false);

		/* this.saveFace=true; */

		putOnLog("Cam Stream Stopped!");

		recogniseBtn.setDisable(true);
		saveBtn.setDisable(true);
		dataPane.setDisable(true);
		stopRecBtn.setDisable(true);
		database.db_close();
		putOnLog("Database Connection Closed");
		isDBready = false;
	}

	private ImageView createImageView(final File imageFile) {

		try {
			final Image img = new Image(new FileInputStream(imageFile), 120, 0, true, true);
			imageView1 = new ImageView(img);

			imageView1.setStyle("-fx-background-color: BLACK");
			imageView1.setFitHeight(120);

			imageView1.setPreserveRatio(true);
			imageView1.setSmooth(true);
			imageView1.setCache(true);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return imageView1;
	}

}

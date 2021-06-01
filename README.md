<div align=center>
	<img src="https://user-images.githubusercontent.com/45324248/120102105-10fc2e80-c17c-11eb-98d7-1e75c2909bd2.jpg" width="200" height="200" alt="logo"></image>
	</br>
	<h3>A Digital Gym System</h3>
	<text>Our Group Project in EBU6304 and also a Digital Service to a traditional gym</text>
</div>
	
## About This Project

This project is a digital gym system for this Group Project in EBU6304, and is also a beautiful artwork written by our group.

### Built with

- [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/) - Java GUI Tool
- [fastJSON](https://github.com/alibaba/fastjson) - used to convert Java Objects into their JSON representation
- [JAVE](http://www.sauronsoftware.it/projects/jave/) - a Java Audio Video Encoder to handle videos in our project
- [JavaFX Windows SDK](https://gluonhq.com/products/javafx/) - used to play videos in our project
- [JUnit](https://junit.org/junit5/) - as an example of using TDD


## Getting Started

### Prerequisites

To run this program, your environment must be/have:
- Windows 10
- JDK 13 ([Java SE 13](https://jdk.java.net/java-se-ri/13))

You need to ensure that you can run `javac` and `java` command in your terminal.

### Installation

There are three ways to compile/run our program.
- Using `jar` file is the simplest way to run and if there is no other requirement, we **recommend** running our program in this way.
- If you want to compile our code manually, you can use the command line tool to compile it and run. This may show some _warnings_ in the command line, but you can ignore them. These warnings are peripheral and are caused by the cache of the video playback component of JavaFX. Our software can still run perfectly. 

#### Using `jar` file we have compiled

If you are willing to visit the [release](https://github.com/kongyijia/DesignAndBuild/releases/) page in our Github repository, you can find a ZIP file, which contains our compiled `jar` file, and also the `data` and `assets` folder the software needed in it. 
If you download this ZIP file, you don't need additional compilation operations, just unzip it and enter the root directory and simply run the software using command line `java -jar DesignAndBuild.jar`. 

For example:
![example of using jar file](https://user-images.githubusercontent.com/45324248/120380851-49566500-c354-11eb-82e9-65ebed140d68.png)


#### Using Command Line

1. Download/Clone our project via QM Hub or [Github](https://github.com/kongyijia/DesignAndBuild/), if you get a ZIP file, please unzip it.
> **If you get more than 2 ZIP file**, you need to put `lib`, `data`, `assets` and `src` folders in a same root directory after unziping it.
> ![Screenshot 2021-06-02 035101](https://user-images.githubusercontent.com/45324248/120382126-dcdc6580-c355-11eb-9210-b18491b28109.png)

2. Enter the root directory via `cmd` and **make sure the working path is the root directory of the project**.
3. Run this command to compile it:
```
javac --add-exports java.desktop/sun.awt=ALL-UNNAMED -Xlint:unchecked -Xlint:deprecation -cp lib/fastjson-1.2.75.jar;lib/jave-1.0.2.jar;lib/JTattoo-1.6.13.jar;lib/apiguardian-api-1.1.0.jar;lib/junit-jupiter-5.7.0.jar;lib/junit-jupiter-api-5.7.0.jar;lib/junit-jupiter-engine-5.7.0.jar;lib/junit-jupiter-params-5.7.0.jar;lib/junit-platform-commons-1.7.0.jar;lib/junit-platform-engine-1.7.0.jar;lib/opentest4j-1.2.0.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx-swt.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.base.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.controls.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.fxml.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.graphics.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.media.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.swing.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.web.jar;lib/javafx-sdk-11.0.2/lib/javafx-swt.jar;lib/javafx-sdk-11.0.2/lib/javafx.base.jar;lib/javafx-sdk-11.0.2/lib/javafx.controls.jar;lib/javafx-sdk-11.0.2/lib/javafx.fxml.jar;lib/javafx-sdk-11.0.2/lib/javafx.graphics.jar;lib/javafx-sdk-11.0.2/lib/javafx.media.jar;lib/javafx-sdk-11.0.2/lib/javafx.swing.jar;lib/javafx-sdk-11.0.2/lib/javafx.web.jar -encoding utf-8 -d out src/*.java src/control/*.java src/control/courseBook/*.java src/control/EditPersonalPageModal/*.java src/control/enroll/*.java src/control/function/*.java src/control/index/*.java src/control/PersonalSchedule/*.java src/control/RecordManage/*.java src/control/staffManage/*.java src/control/Userinformation/*.java src/control/videoManagement/*.java src/control/VideoSquare/*.java src/control/FX_Video/*.java src/control/getVIP/*.java src/model/*.java src/model/mapping/*.java src/util/*.java src/util/Video/*.java src/view/*.java src/view/basicComponents/*.java src/view/courseBook/*.java src/view/editPersonalModal/*.java src/view/function/*.java src/view/Schedule/*.java src/view/staffManagement/*.java src/view/Userinformation/*.java src/view/videoManagement/*.java src/view/videoManagement/uploadVideo/*.java src/view/videoManagement/videoManagement/*.java src/view/VideoSquare/*.java src/view/vip/*.java src/view/buyVip/*.java
```
> You can ignore the warnings that appear while compiling. This does not affect the use of the software.

4. Run this command to start using our software:
```
java -Dfile.encoding=UTF-8 -classpath lib/fastjson-1.2.75.jar;lib/jave-1.0.2.jar;lib/JTattoo-1.6.13.jar;lib/apiguardian-api-1.1.0.jar;lib/junit-jupiter-5.7.0.jar;lib/junit-jupiter-api-5.7.0.jar;lib/junit-jupiter-engine-5.7.0.jar;lib/junit-jupiter-params-5.7.0.jar;lib/junit-platform-commons-1.7.0.jar;lib/junit-platform-engine-1.7.0.jar;lib/opentest4j-1.2.0.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx-swt.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.base.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.controls.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.fxml.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.graphics.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.media.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.swing.jar;lib/Winjavafx-sdk-11.0.2/lib/javafx.web.jar;lib/javafx-sdk-11.0.2/lib/javafx-swt.jar;lib/javafx-sdk-11.0.2/lib/javafx.base.jar;lib/javafx-sdk-11.0.2/lib/javafx.controls.jar;lib/javafx-sdk-11.0.2/lib/javafx.fxml.jar;lib/javafx-sdk-11.0.2/lib/javafx.graphics.jar;lib/javafx-sdk-11.0.2/lib/javafx.media.jar;lib/javafx-sdk-11.0.2/lib/javafx.swing.jar;lib/javafx-sdk-11.0.2/lib/javafx.web.jar;out MainApp
```
5. Please enjoy.


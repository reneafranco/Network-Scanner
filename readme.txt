Network Scanner Application Documentation
Overview
This documentation provides detailed information on the Network Scanner Application, including its features, usage, and implementation details. The application is designed to perform various types of network scans using Nmap and provides a graphical user interface for ease of use.

Table of Contents
Introduction
Installation
Features
Usage
Fast Scan
Advanced Scan
Network Scan
File Selection
Theme Toggle
Code Structure
FXML Layouts
Appendix
Disclaimer
Requirements
Common Issues
Introduction
The Network Scanner Application is a JavaFX-based application that utilizes Nmap for network scanning tasks. It allows users to perform fast scans, advanced scans, and network scans, and view scan results directly within the application. The application also includes a file explorer to navigate and view scan result files and supports dark mode for improved usability.

Installation
Prerequisites
Java Development Kit (JDK) 8 or higher
JavaFX SDK
Nmap installed on the system and available in the system PATH
Steps
Clone the repository:
sh
Copy code
git clone https://github.com/reneafranco/Network-Scanner.git
Navigate to the project directory:
sh
Copy code
cd network-scanner
Build the project using your preferred IDE or command line tool.
Features
Fast Scan: Perform a quick scan of the target IP.
Advanced Scan: Perform a detailed scan of the target IP on a specified port.
Network Scan: Scan an entire network to find active hosts.
File Explorer: Navigate through scan result files.
Theme Toggle: Switch between light and dark mode.
Usage
Fast Scan
To perform a fast scan:

Navigate to the Fast Scan view using the sidebar.
Enter the target IP address in the text field.
Click the Submit button.
The scan results will be displayed in the text area and saved to a file in the fastScan directory.
Advanced Scan
To perform an advanced scan:

Navigate to the Advanced Scan view using the sidebar.
Enter the target IP address and the port number in the respective text fields.
Click the Submit button.
The scan results will be displayed in the text area and saved to a file in the advanceScan directory.
Network Scan
To perform a network scan:

Navigate to the Network Scan view using the sidebar.
Enter the network IP range (e.g., 192.168.1.0/24) in the text field.
Click the Submit button.
The scan results will be displayed in the text area and saved to a file in the networkScan directory.
File Selection
To view the contents of a scan result file:

Select a file from the TreeView on the right side of the application.
The file's content will be displayed in the text area.
Theme Toggle
To toggle between light and dark modes:

Use the toggle switch located in the bottom bar.
The application's theme will change accordingly.

Disclaimer
The authors of this application are not responsible for any misuse of the software. Users are advised to use it responsibly and in compliance with local laws and regulations. By using this application, users agree to these terms and conditions.

Requirements
Operating System: Windows, macOS, Linux
Java Version: JDK 8 or higher
Nmap: Installed and available in system PATH
Common Issues
Progress Bar Stuck: Ensure the Nmap command is executed correctly and check the console for errors.
File Not Found: Verify the file paths and permissions.
UI Freezes: Long-running Nmap commands may cause the UI

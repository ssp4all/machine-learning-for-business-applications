The following tools might be required to complete the projects.

* Apache Spark (Version 1.6.2-hadoop2.6 and Version 2.0.0-hadoop2.6)
* Apache Kafka (Version 2.10-0.10.1.0)
* Apache Zookeeper (Version 3.4.10)
---------------------------------------------------------------------------------

Here is a sample installation guide for linux users. You may use VMWare, local linux or vcl for this.

Open the terminal window and cd into the folder 'setup-files'. Run the following commands to install the required tools/dependencies on your system.

source vm_install.sh

source ~/.bash_profile ~/.bashrc 

---------------------------------------------------------------------------------

EXECUTION STEPS

The tools and dependencies required for this project have been pre-installed. To check whether these tools are working properly, we have written some easy-to-execute steps in the 'install-check' folder. To execute them, do the following.

Click the files icon in the dock on the left of your screen. In the Home directory, enter the folder titled 'install-check'. Inside you will find five folders: kafka, spark, mongodb, spark-mongodb and paths. Each of these folders contain a README file. Open each one by one and follow the steps carefully. Make sure to close all terminals once you are done with one folder. For example, if you finish running the steps in the kafka folder, close all terminals and open a new one to execute steps mentioned in the spark folder. Please follow the order mentioned below.

1. paths - The README file in this folder consists of commands that can be executed to check whether paths have been set correctly. Execute line by line to see if the path set is correct.

2. kafka - The README file in this folder consists of commands that can be executed to check whether the tool/service is working as required. Execute line by line to see if tool/service is working.

3. mongodb - The README file in this folder consists of commands that can be executed to check whether the tool/service is working as required. Execute line by line to see if tool/service is working.

4. spark - The README file in this folder consists of commands that can be executed to check whether the tool/service is working as required. Execute line by line to see if tool/service is working.

5. spark-mongdb - This file consists of commands that can be executed to check if spark and mongodb work together. Execute line by line to see if tool/service is working.
---------------------------------------------------------------------------------

ADDITIONAL FOLDERS

1. setup-files

* vm_install.sh - This is the main installation file used to install all tools and dependencies used in for all the projects in this workshop.
* requirements.txt - This file lists all dependencies used for all the projects in this workshop. This file is required by the vm_install.sh script.




# Email_Generator Bash script amazonlinux:2
The build of the process can be made using bash scripts. This automate the whole process of installing the pre-reqisites and setting project and then creating its build.

## Pre-Requisites
* Java
* Gradle
* Jdk 17
* Spring

## Server Deployment

First you have to copy `Bash_scripts` folder on the Server.
 
1. `run_script.sh` script file is used to automate the proess of creating build and Deployment folder of the `qss4` process.<br>
it run all the script files 
`pre-requirement_installation_script` and `build_script.sh` internally one by one . 

2. `run_script.sh` file automatically check all the pre-requisites required to deploy the process and install if any of them are missing .

3. To run the `run_script.sh` file you must have provide the parameters with it which is:

* `Branch Name`: This prameter is used to specify the specific branch of the `qss4` process to be cloned for the Deployment process or You can pass `main` or `MAIN` as a parameter to clone the main branch.

Pass all the parameters as follow :<br>

```
run_script.sh <branch_name> 
```

all the perameters are required so pass all the parametes and also in the same order as it mentioned above. <br>

Pass the valid branch name or pass the `main` as branch name otherwise the script file will not be executed.
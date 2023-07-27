#/bin/bash
yum update
check_package() {
    if which $1 >/dev/null 2>&1; then
        echo "$1 is already installed"
    else
        echo "Installing $1..."
          yum install -y $1
        if [ $? -ne 0 ]; then
            echo "Error: Failed to install $1"
            exit 1
        fi
        echo "$1 has been installed"
    fi
}

# Check and install Git
check_package "git"

if type -p java; then
    echo "Java is installed"
    if [[ $(java -version 2>&1) == "17" ]]; then
        echo "Java version 17 is installed"
    else
     yum install -y wget
     echo "Java version 17 is not installed"
	 wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz"
	 yum install -y tar
	 tar -xf jdk-17_linux-x64_bin.tar.gz -C /tmp
    fi
else
    echo "Java is not installed"
         yum install -y wget
         wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz"
         yum install -y tar
         tar -xf jdk-17_linux-x64_bin.tar.gz -C /tmp
    echo "java is installed Successfully"
fi
if type -p gradle >/dev/null 2>&1; then
    if [[ $(gradle --version | grep "Gradle 8.1.1") ]]; then
        echo "Gradle version 8.1.1 is installed"
    else
        echo "Gradle version 8.1.1 is not installed, installing it now"
        yum remove -y gradle
        yum install -y wget
        wget https://services.gradle.org/distributions/gradle-8.1.1-bin.zip
        yum install -y unzip
        unzip gradle-8.1.1-bin.zip -d /tmp
        echo gradle file unziped
    fi
else
    echo "Gradle is not installed, installing it now"
    yum install -y wget
    wget https://services.gradle.org/distributions/gradle-8.1.1-bin.zip
    yum install -y unzip
    unzip gradle-8.1.1-bin.zip -d /tmp
    echo gradle file unziped
    export PATH=$PATH:/tmp/gradle-8.1.1/bin
fi
echo system for process is done.
# PIDR_Kessler_Meyer

PIDR about ICN search engine

## Pre-requisites
To use that you ABSOLUTELY need to install a few things

- For Debian based (Ubuntu, Kali Linux, Tails):

 -  ```sh
    sudo apt install build-essential libssl-dev libsqlite3-dev pkg-config cmake patchelf libsystemd-dev libpcap-dev
     ```
- For Fedora:
 -  ```sh
    sudo yum install gcc-g++ sqlite-devel cmake openssl-devel patchelf make
     ```
- For FreeBDS:
 -  ```sh
    sudo pkg install python pkgconf sqlite3 cmake patchelf gcc g++ make linux-headers openssl-dev libsystemd-dev libpcap-dev
     ```
- For Alpine:
 -  ```sh
    apk add python pkgconf sqlite-dev gcc cmake patchelf g++ make linux-headers openssl-dev libpcap-dev
     ```
- For Pacman based helper:
 -  ```sh
    pacman -S pkgconf gcc cmake python sqlite patchelf openssl make libpcap
     ```
    
- For Yay based helper:
 -  ```sh
    yay -S pkgconf gcc cmake python sqlite patchelf openssl make libpcap
     ```
    
## RUN
The first make will take a while since it requires to make boost and libndn, subsequent build will be way faster.
The build process can take up to 2GB of space and take up to 10min (the compilation is not speed up by default but you 
can change so in the cmake or by overriding the SPEED variable cmake -DSPEED=6).
The build process do require an internet connection for the first time it runs.
  - ```sh
    cmake .
    ```
  - ```sh
    make client
    ```
  - ```sh
    make server
    ```
Once those command are done, you can now run as many client and server as you want, you just need to specify a 
filesystem to work with.


## EXTRAS
To clean you can just type `cmake . && make Clean` (beware of the Capital letter)

    
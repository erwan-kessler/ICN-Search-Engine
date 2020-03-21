# PIDR_Kessler_Meyer

PIDR about ICN search engine


To use that you ABSOLUTELY need to install a few things

- For Debian based (Ubuntu, Kali Linux, Tails):

 -  ```sh
    sudo apt install build-essential libssl-dev libsqlite3-dev pkg-config cmake patchelf
     ```
- For Fedora:
 -  ```sh
    sudo yum install gcc-g++ sqlite-devel cmake openssl-devel patchelf
     ```
- For FreeBDS:
 -  ```sh
    sudo pkg install python pkgconf sqlite3 cmake patchelf
     ```
- For Alpine:
 -  ```sh
    pkg add python pkgconf sqlite3 cmake patchelf
     ```
- For Pacman based helper:
 -  ```sh
    pacman -S pkgconf gcc cmake python sqlite patchelf
     ```
    
- For Yay based helper:
 -  ```sh
    yay -S pkgconf gcc cmake python sqlite patchelf
     ```
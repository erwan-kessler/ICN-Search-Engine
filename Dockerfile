# CLion remote docker environment (How to build docker container, run and stop it)
#
# Build and run:
#   docker build -t neilpop/clion_ndn -f Dockerfile .
#   docker run -d --cap-add sys_ptrace -p 127.0.0.1:2233:22 --name clion --privileged -v /sys/fs/cgroup:/sys/fs/cgroup:ro neilpop/clion_ndn
#   ssh-keygen -f "$HOME/.ssh/known_hosts" -R "[localhost]:2233"
#
# stop:
#   docker stop clion
# 
# ssh user@127.0.0.1 -p 2233
# 
# ssh credentials (test user):
#   user@password 



FROM jrei/systemd-ubuntu:18.04
MAINTAINER Erwan KESSLER


ENV HOME /root

RUN apt-get update \
  && apt-get install -yq build-essential \
    gcc \
    g++ \
    gdb \
    clang \
    cmake \
    rsync \
    tar \
    python \
    openssh-server \
    git \
    nano \
    libboost-all-dev \
    libssl-dev \
    libsqlite3-dev \
    pkg-config \
    patchelf \
    libpcap-dev \
    software-properties-common


RUN add-apt-repository -y ppa:named-data/ppa
RUN apt update
RUN apt -yq install nfd ndn-cxx
RUN apt-get clean


# SSH
RUN ( \
    echo 'LogLevel DEBUG2'; \
    echo 'PermitRootLogin yes'; \
    echo 'PasswordAuthentication yes'; \
    echo 'Subsystem sftp /usr/lib/openssh/sftp-server'; \
  ) > /etc/ssh/sshd_config

RUN useradd -m user && yes password | passwd user
RUN usermod -aG sudo user

RUN service ssh restart

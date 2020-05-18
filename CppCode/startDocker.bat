docker info

docker kill clion
docker rm clion

docker run -d --cap-add sys_ptrace -p 127.0.0.1:2233:22 --name clion --privileged -v /sys/fs/cgroup:/sys/fs/cgroup:ro neilpop/clion_ndn
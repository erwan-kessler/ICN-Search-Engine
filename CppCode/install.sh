# sudo apt-get install build-essential pkg-config libboost-all-dev libsqlite3-dev libssl-dev libpcap-dev python-minimal
SPEED=4
mkdir temp && cd temp
git clone https://github.com/named-data/ndn-cxx && cd ndn-cxx
git checkout tags/ndn-cxx-0.7.0
./waf configure
./waf -j $SPEED
sudo ./waf install
sudo ldconfig
cd ..
git clone --recursive https://github.com/named-data/NFD && cd NFD
git checkout tags/NFD-0.7.0
./waf configure
./waf -j $SPEED
sudo ./waf install
cd ../..
rm -rf temp

sudo cp /usr/local/etc/ndn/nfd.conf.sample /usr/local/etc/ndn/nfd.conf

nfd-start

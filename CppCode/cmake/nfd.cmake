include(ExternalProject)
set(nfd_GIT_URL "https://github.com/named-data/NFD")
set(nfd_GIT_TAG "fb03421") # 0.7.0
set(nfd_INSTALL "${CMAKE_CURRENT_BINARY_DIR}/third_party/nfd")
set(nfd_INCLUDE_DIR ${nfd_INSTALL}/include)
set(nfd_LIB_DIR ${nfd_INSTALL}/lib)
set(nfd_BIN_DIR ${nfd_INSTALL}/bin)

ExternalProject_Add(nfd
        PREFIX nfd
        GIT_REPOSITORY ${nfd_GIT_URL}
        GIT_TAG ${nfd_GIT_TAG}
        BUILD_IN_SOURCE 1
        CONFIGURE_COMMAND
        export PKG_CONFIG_PATH=${ndn_LIB_DIR}/pkgconfig:$PKG_CONFIG_PATH  &&
        ./waf configure --prefix=<INSTALL_DIR> --boost-includes=${boost_INCLUDE_DIR} --boost-libs=${boost_LIB_DIR}  --without-systemd
        BUILD_COMMAND
        ./waf -p -j ${SPEED}
        INSTALL_COMMAND
        ./waf install &&
        patchelf --set-rpath ${boost_LIB_DIR} ${nfd_BIN_DIR}/ndn-autoconfig &&
        patchelf --set-rpath ${boost_LIB_DIR} ${nfd_BIN_DIR}/nfd &&
        patchelf --set-rpath ${boost_LIB_DIR} ${nfd_BIN_DIR}/nfd-autoreg &&
        patchelf --set-rpath ${boost_LIB_DIR} ${nfd_BIN_DIR}/nfdc
        INSTALL_DIR ${nfd_INSTALL})


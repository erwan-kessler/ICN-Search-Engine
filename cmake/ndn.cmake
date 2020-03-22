include(ExternalProject)
set(ndn_URL "https://github.com/named-data/ndn-cxx/archive/ndn-cxx-0.7.0.zip")
set(ndn_INSTALL "${CMAKE_CURRENT_BINARY_DIR}/third_party/ndn")
set(ndn_INCLUDE_DIR ${ndn_INSTALL}/include)
set(ndn_LIB_DIR ${ndn_INSTALL}/lib)

ExternalProject_Add(ndn
        PREFIX ndn
        URL ${ndn_URL}
        BUILD_IN_SOURCE 1
        CONFIGURE_COMMAND
        ./waf configure --prefix=<INSTALL_DIR> --boost-includes=${boost_INCLUDE_DIR} --boost-libs=${boost_LIB_DIR}
        BUILD_COMMAND
        ./waf -p -j ${SPEED}
        INSTALL_COMMAND
        ./waf install &&
        # This is a dirty fix, they need to fix their wscript
        patchelf --set-rpath ${boost_LIB_DIR} ${ndn_LIB_DIR}/libndn-cxx.so
        INSTALL_DIR ${ndn_INSTALL})
set(ndn_LIBRARIES ${ndn_LIB_DIR}/libndn-cxx.so)

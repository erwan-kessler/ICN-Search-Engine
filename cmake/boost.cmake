include(ExternalProject)

set(boost_URL "https://dl.bintray.com/boostorg/release/1.72.0/source/boost_1_72_0.tar.bz2")
set(boost_SHA256 "59c9b274bc451cf91a9ba1dd2c7fdcaf5d60b1b3aa83f2c9fa143417cc660722")
set(boost_INSTALL ${CMAKE_CURRENT_BINARY_DIR}/third_party/boost)
set(boost_INCLUDE_DIR ${boost_INSTALL}/include)
set(boost_LIB_DIR ${boost_INSTALL}/lib)

ExternalProject_Add(boost
        PREFIX boost
        URL ${boost_URL}
        URL_HASH SHA256=${boost_SHA256}
        BUILD_IN_SOURCE 1
        CONFIGURE_COMMAND
        ./bootstrap.sh
        --with-libraries=chrono
        --with-libraries=filesystem
        --with-libraries=thread
        --with-libraries=log
        --with-libraries=stacktrace
        --with-libraries=system
        --with-libraries=program_options
        # edit here the libs needed by ndn
        --prefix=<INSTALL_DIR>
        BUILD_COMMAND
        ./b2 install link=shared variant=release threading=multi runtime-link=shared # by default
        INSTALL_COMMAND ""
        INSTALL_DIR ${boost_INSTALL})

list(APPEND Boost_LIBRARIES
        ${boost_LIB_DIR}/libboost_atomic.so
        ${boost_LIB_DIR}/libboost_chrono.so
        ${boost_LIB_DIR}/libboost_filesystem.so
        ${boost_LIB_DIR}/libboost_thread.so
        ${boost_LIB_DIR}/libboost_log.so
        ${boost_LIB_DIR}/libboost_stacktrace_backtrace.so
        ${boost_LIB_DIR}/libboost_system.so
        ${boost_LIB_DIR}/libboost_program_options.so)
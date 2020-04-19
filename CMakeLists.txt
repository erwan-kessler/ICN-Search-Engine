cmake_minimum_required(VERSION 3.10)

project("ICN search engine" CXX)
set(CMAKE_VERBOSE_MAKEFILE ON)
# We expect standard 14 and above and we make sure to have the debugging flag set
set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} "-O2 -ggdb3 -pedantic -Wall -Wextra -Wnon-virtual-dtor -Wno-unused-parameter")

# include the scripts needed for the build of their relatives libraries
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} ${CMAKE_CURRENT_SOURCE_DIR}/cmake)

set(DOCKER 1)
if (NOT DOCKER)
    # Set here the number of cores to use (default is 1)
    if (NOT DEFINED SPEED)
        set(SPEED 10)
    endif ()
    include(boost)
    include(ndn)
    include(nfd)

    # Chain to build ndn and make it a shared library target, depends of libboost below
    ADD_LIBRARY(libndn SHARED IMPORTED)
    SET_TARGET_PROPERTIES(libndn PROPERTIES IMPORTED_LOCATION ${ndn_LIBRARIES})
    add_dependencies(libndn nfd)
    add_dependencies(nfd ndn)
    add_dependencies(ndn libboost)
    add_custom_command(TARGET libndn POST_BUILD COMMAND ${nfd_INSTALL}/bin/nfd-start)

    # This whole chain can be removed and replaced with a dependency to boost only with the correct change below in NDN_LIBS
    add_library(libboost::chrono UNKNOWN IMPORTED)
    set_target_properties(libboost::chrono PROPERTIES IMPORTED_LOCATION "${boost_LIB_DIR}/libboost_chrono.so")

    add_library(libboost::filesystem UNKNOWN IMPORTED)
    set_target_properties(libboost::filesystem PROPERTIES IMPORTED_LOCATION "${boost_LIB_DIR}/libboost_filesystem.so")

    add_library(libboost::thread UNKNOWN IMPORTED)
    set_target_properties(libboost::thread PROPERTIES IMPORTED_LOCATION "${boost_LIB_DIR}/libboost_thread.so")

    add_library(libboost::log UNKNOWN IMPORTED)
    set_target_properties(libboost::log PROPERTIES IMPORTED_LOCATION "${boost_LIB_DIR}/libboost_log.so")

    add_library(libboost::stacktrace UNKNOWN IMPORTED)
    set_target_properties(libboost::stacktrace PROPERTIES IMPORTED_LOCATION "${boost_LIB_DIR}/libboost_stacktrace_basic.so")

    ADD_LIBRARY(libboost INTERFACE IMPORTED)
    set_property(TARGET libboost PROPERTY INTERFACE_LINK_LIBRARIES
            libboost::chrono libboost::filesystem libboost::thread libboost::log libboost::stacktrace)
    add_dependencies(libboost boost)


    # Debug message in case you need specific access to libraries
    message(STATUS "Boost Headers are here: " ${boost_INCLUDE_DIR})
    message(STATUS "Boost Libs are here: " ${boost_LIB_DIR})
    message(STATUS "Ndn Headers are here: " ${ndn_INCLUDE_DIR})
    message(STATUS "Ndn Libs are here: " ${ndn_LIB_DIR})
    message(STATUS "Boost linked libs are here: " ${Boost_LIBRARIES})
    message(STATUS "Ndn linked lib is here: " ${ndn_LIBRARIES})

    # The headers are set by the included scripts
    list(APPEND NDN_INCLUDE_DIRS ${boost_INCLUDE_DIR} ${ndn_INCLUDE_DIR})
    # Those libraries were built before and can now be linked
    list(APPEND NDN_LIBS libndn libboost) # could use ${Boost_LIBRARIES} instead of libboost if you remove the chain
else ()
    find_package(Boost REQUIRED)
    find_package(ndn-cxx 0.7.0 REQUIRED)

    ADD_LIBRARY(libndn SHARED IMPORTED)
    SET_TARGET_PROPERTIES(libndn PROPERTIES IMPORTED_LOCATION ${ndn_LIBRARIES})

    list(APPEND NDN_INCLUDE_DIRS ${Boost_INCLUDE_DIRS} ${ndn_INCLUDE_DIR})
    list(APPEND NDN_LIBS -lboost_system libndn )

    message("ndn shared library is here :" "${NDN_LIBS}")
    message("ndn include headers are here :" "${NDN_INCLUDE_DIRS}")

    # find_package(Threads)
endif ()


#adding new executables and linking
add_executable(server server.cpp)
include_directories(server PUBLIC ${NDN_INCLUDE_DIRS})
target_link_libraries(server ${NDN_LIBS})

add_executable(client client.cpp)
include_directories(client PUBLIC ${NDN_INCLUDE_DIRS})
target_link_libraries(client ${NDN_LIBS})


# Cleaning task
file(WRITE null.cpp "int main() { return 0; }")
add_executable(Clean null.cpp)
add_custom_command(TARGET Clean POST_BUILD
        COMMAND ${CMAKE_COMMAND} -P ${CMAKE_CURRENT_SOURCE_DIR}/cmake/clean-all.cmake
        COMMAND ${CMAKE_COMMAND} ${CMAKE_SOURCE_DIR})

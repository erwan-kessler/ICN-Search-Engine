cmake_minimum_required(VERSION 3.10)

project("ICN search engine" CXX)
set(CMAKE_VERBOSE_MAKEFILE ON)
# We expect standard 17 and above and we make sure to have the debugging flag set
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} "-ggdb3 -Wall ")

# include the scripts needed for the build of their relatives libraries
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} ${CMAKE_CURRENT_SOURCE_DIR}/cmake)
include(boost)
include(ndn)

# Chain to build ndn and make it a shared library target, depends of libboost above
ADD_LIBRARY(libndn SHARED IMPORTED) # this a dummy target so it is run
SET_TARGET_PROPERTIES(libndn PROPERTIES IMPORTED_LOCATION ${ndn_LIBRARIES})
add_dependencies(libndn ndn)
add_dependencies(ndn boost)

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
list(APPEND NDN_LIBS libndn ${Boost_LIBRARIES})

#adding new executables and linking
add_executable(server server.cpp)
include_directories(server PUBLIC ${NDN_INCLUDE_DIRS})
target_link_libraries(server ${NDN_LIBS})

add_executable(client client.cpp)
include_directories(client PUBLIC ${NDN_INCLUDE_DIRS})
target_link_libraries(client ${NDN_LIBS})

# this command fails so you dont have any issue with the build step starting again
add_custom_target(clean-all COMMAND ${CMAKE_BUILD_TOOL} clean COMMAND ${CMAKE_COMMAND} -P ${CMAKE_CURRENT_SOURCE_DIR}/cmake/clean-all.cmake)
#!/bin/sh

MODULE_NAME=deer-service_0.1

echo "Building Debian package for ${MODULE_NAME}"
rm -rf ../target/debian

# add the Debian control files
cp -r debian ../target/

# build the package and sign it
cd ../target
debuild --check-dirname-level 0 -b

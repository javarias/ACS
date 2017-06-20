#!/bin/bash

if [ -z $CROSS_COMPILE ]; then
	echo "This script is intended to be used with a cross compiler environment only"
	exit -1
fi

# rm -rf $ACE_ROOT
mkdir -p $ACE_ROOT

tar -xvzf ../ExtProd/PRODUCTS/ACE+TAO-6.3.0.tar.gz -C $ACE_ROOT/..


cp platform_macros.GNU $ACE_ROOT/TAO/ACE_wrappers/build/linux/include/makeinclude/platform_macros.GNU
cp config.h $ACE_ROOT/TAO/ACE_wrappers/build/linux/ace/config.h
cp platform_linux_common.GNU $ACE_ROOT/TAO/ACE_wrappers/include/makeinclude/platform_linux_common.GNU
cp platform_linux.GNU $ACE_ROOT/TAO/ACE_wrappers/include/makeinclude/platform_linux.GNU

cd $ACE_ROOT/ace; make -j3
cd $ACE_ROOT/ACEXML; make -j3
cd $ACE_ROOT/TAO/tao; make -j3
cd $ACE_ROOT/TAO/orbsvcs; make -j3
cd $ACE_ROOT/TAO/utils; make -j3

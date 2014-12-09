#include $(ACE_ROOT)/include/makeinclude/wrapper_macros.GNU
CROSS_COMPILE := armv6-rpi-linux-gnueabi-
HOST_ROOT := $(ACSROOT)/../TAO/ACE_wrappers/build/linux

no_hidden_visibility=1

include $(ACE_ROOT)/include/makeinclude/platform_linux.GNU


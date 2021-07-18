DESCRIPTION = "HardKernel Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

KBRANCH = "odroidxu4-4.9.y"

SRC_URI = "git://github.com/hardkernel/linux.git;branch=${KBRANCH} \
    https://dn.odroid.com/toolchains/gcc-linaro-arm-linux-gnueabihf-4.9-2014.09_linux.tar.xz \
    "
#http://dn.odroid.com/ODROID-XU/compiler/arm-eabi-4.6.tar.gz
SRCREV = "36c916d8319750d911433999573c79194147aee0"
SRC_URI[sha256sum] = "0cffac0caea0eb3c8bdddfa14be011ce366680f40aeddbefc7cf23cb6d4f1891"
#SRC_URI[sha256sum] = "4df101f7defe41f28551f78cf8a2da32c95fd85e6ad1ad4a8b7ef6564ca0b6f6"

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONF_BSP_AUDIT_LEVEL = "0"

inherit kernel

S = "${WORKDIR}/git"
B = "${S}"

KBUILD_DEFCONFIG = "odroidxu4_defconfig"
LINUX_VERSION ?= "4.9.y"

HOSTTOOLS += " bc "

DEPENDS += " bc-native lzop-native "
KERNEL_EXTRA_FEATURES = ""

TOOLCHAIN_PREFIX = "arm-linux-gnueabihf-"
#TOOLCHAIN_PREFIX = "arm-eabi-"

COMPILER = "${WORKDIR}/gcc-linaro-arm-linux-gnueabihf-4.9-2014.09_linux/bin/arm-linux-gnueabihf-gcc"
#COMPILER = "${WORKDIR}/arm-eabi-4.6/bin/arm-eabi-gcc"

EXTRA_OECONF = ""
EXTRA_OEMAKE = ' CROSS_COMPILE="${TOOLCHAIN_PREFIX}" \
	CC="${COMPILER}" \
	ARCH=arm \
	CFLAGS="" \
	LDFLAGS="" \
	HOSTCC="${BUILD_CC}" CPUS=${@oe.utils.cpu_count()} V=1 \
    '
#CC="${COMPILER}"
#CFLAGS="-w -Wno-error"
#CXXFLAGS="-w -Wno-error"
#CPPFLAGS="-w -Wno-error"
#LDFLAGS=""

#CPUS=${@oe.utils.cpu_count()}
#KBUILD_CFLAGS="-w -Wno-error"
LINAROTOOLCHAIN = "4.9"

#PATH_prepend = "${WORKDIR}/arm-eabi-4.6/bin:"
PATH_prepend = "${WORKDIR}/gcc-linaro-arm-linux-gnueabihf-4.9-2014.09_linux/bin:"

do_configure () {
    ln -s "${COMPILER}" "${WORKDIR}/gcc-linaro-arm-linux-gnueabihf-4.9-2014.09_linux/bin/arm-oe-linux-gnueabi-gcc" | true
    #oe_runmake mrproper
	oe_runmake ${KBUILD_DEFCONFIG}
}

do_compile () {
    TARGET_SYS="arm-linux-gnueabihf" oe_runmake
}


COMPATIBLE_MACHINE = "odroid-xu4"
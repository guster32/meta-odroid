#Mali userland provides these
PROVIDES:remove  = "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'virtual/libgbm virtual/libgles1 virtual/libgles2 virtual/egl virtual/libegl', '', d)}"
do_install:append:odroid () {
    if [ -n "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'mali', '', d)}" ]; then
        rm -f ${D}/${libdir}/libEGL*
        rm -f ${D}/${libdir}/libGLESv1_CM.*
        rm -f ${D}/${libdir}/libGLESv2.*
        rm -f ${D}/${libdir}/libgbm*
        rm -f ${D}${libdir}/pkgconfig/glesv1_cm.pc
        rm -f ${D}${libdir}/pkgconfig/egl.pc
        rm -f ${D}${libdir}/pkgconfig/glesv2.pc
        rm -f ${D}${includedir}/EGL/eglext.h 
        rm -f ${D}${includedir}/EGL/eglmesaext.h
        rm -f ${D}${includedir}/EGL/egl.h
        rm -f ${D}${includedir}/EGL/eglplatform.h
        rm -f ${D}${includedir}/GLES2/gl2ext.h
        rm -f ${D}${includedir}/GLES2/gl2platform.h
        rm -f ${D}${includedir}/GLES2/gl2.h

        rm -rf ${D}${includedir}/GLES3

        rm -f ${D}${includedir}/GLES/egl.h
        rm -f ${D}${includedir}/GLES/gl.h
        rm -f ${D}${includedir}/GLES/glext.h
        rm -f ${D}${includedir}/GLES/glplatform.h
        rm -f ${D}${includedir}/KHR/khrplatform.h
    	if [ -n "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}" ]; then
            rm -f ${D}/${libdir}/libwayland-egl*
        fi
    fi
}

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS     += -llog \
                    -lGLESv1_CM
LOCAL_MODULE     := player
LOCAL_C_INCLUDES := $(LOCAL_PATH)

NDK_APP_DST_DIR := ../jniLibs/$(TARGET_ARCH_ABI)

LOCAL_SRC_FILES :=  com_vr_player_ndk_NativeApi.cpp   \
                    aes/aes.cpp                       \
                    aes/licence.cpp                   \
                    map/map_t_original.cpp            \
                    map/map_t_down.cpp                \
                    map/map_t_front.cpp               \
                    map/map_t_plane_up_down.cpp       \
                    map/map_t_up.cpp                  \
                    map/map_t_up_down.cpp             \
                    map/map_v_cylindrical.cpp         \
                    map/map_v_plane.cpp               \
                    map/map_v_spherical.cpp           \
                    map/map_t_original_4k.cpp         \
                    map/map_t_down_4k.cpp             \
                    map/map_t_front_4k.cpp            \
                    map/map_t_plane_up_down_4k.cpp    \
                    map/map_t_up_4k.cpp               \
                    map/map_t_up_down_4k.cpp          \
                    map/map_v_cylindrical_4k.cpp      \
                    map/map_v_plane_4k.cpp            \
                    map/map_v_spherical_4k.cpp        \
                    ball_graph.cpp                    \
                    gl_util.cpp

include $(BUILD_SHARED_LIBRARY)

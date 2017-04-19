LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS     += -llog \
                    -lGLESv1_CM
LOCAL_MODULE     := player
LOCAL_C_INCLUDES := $(LOCAL_PATH)

NDK_APP_DST_DIR := ../jniLibs/$(TARGET_ARCH_ABI)

LOCAL_SRC_FILES :=  com_vr_player_ndk_NativeApi.c   \
                    aes/aes.c                       \
                    map/map_t_original.c            \
                    map/map_t_down.c                \
                    map/map_t_front.c               \
                    map/map_t_plane_up_down.c       \
                    map/map_t_up.c                  \
                    map/map_t_up_down.c             \
                    map/map_v_cylindrical.c         \
                    map/map_v_plane.c               \
                    map/map_v_spherical.c           \
                    map/map_t_original_4k.c         \
                    map/map_t_down_4k.c             \
                    map/map_t_front_4k.c            \
                    map/map_t_plane_up_down_4k.c    \
                    map/map_t_up_4k.c               \
                    map/map_t_up_down_4k.c          \
                    map/map_v_cylindrical_4k.c      \
                    map/map_v_plane_4k.c            \
                    map/map_v_spherical_4k.c        \
                    ball_graph.c                    \
                    gl_util.c

include $(BUILD_SHARED_LIBRARY)

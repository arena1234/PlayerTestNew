#ifndef MAP_H_
#define MAP_H_

#include <GLES/gl.h>

#ifdef __cplusplus
#if __cplusplus
extern "C" {
#endif
#endif /* Begin of #ifdef __cplusplus */

extern GLfloat k4k_spherical_vertex[1640 * 3];
extern GLfloat k4k_cylindrical_vertex[1640 * 3];
extern GLfloat k4k_plane_vertex[1640 * 3];

extern GLfloat k4k_spherical_front_Texture[1640 * 2];
extern GLfloat k4k_spherical_up_Texture[1640 * 2];
extern GLfloat k4k_spherical_down_Texture[1640 * 2];
extern GLfloat k4k_spherical_up_down_Texture[1640 * 2];
extern GLfloat k4k_plane_up_down_Texture[1640 * 2];
extern GLfloat k4k_plane_original_Texture[1640 * 2];

extern GLfloat spherical_vertex[1640 * 3];
extern GLfloat cylindrical_vertex[1640 * 3];
extern GLfloat plane_vertex[1640 * 3];

extern GLfloat spherical_front_Texture[1640 * 2];
extern GLfloat spherical_up_Texture[1640 * 2];
extern GLfloat spherical_down_Texture[1640 * 2];
extern GLfloat spherical_up_down_Texture[1640 * 2];
extern GLfloat plane_up_down_Texture[1640 * 2];
extern GLfloat plane_original_Texture[1640 * 2];

#ifdef __cplusplus
#if __cplusplus
}
#endif
#endif /* End of #ifdef __cplusplus */


#endif /* MEDIA_INIT_COMMON_H_ */

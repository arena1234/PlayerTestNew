#include "map.h" 
GLfloat spherical_up_Texture[1640*2] = { 
0.508203,0.472917,
0.531641,0.472917,
0.508203,0.472917,
0.531352,0.479435,
0.508203,0.472917,
0.530494,0.485792,
0.508203,0.472917,
0.529086,0.491833,
0.508203,0.472917,
0.527164,0.497408,
0.508203,0.472917,
0.524776,0.502379,
0.508203,0.472917,
0.521979,0.506626,
0.508203,0.472917,
0.518844,0.510042,
0.508203,0.472917,
0.515446,0.512544,
0.508203,0.472917,
0.511870,0.514070,
0.508203,0.472917,
0.508203,0.514583,
0.508203,0.472917,
0.504537,0.514070,
0.508203,0.472917,
0.500961,0.512544,
0.508203,0.472917,
0.497563,0.510042,
0.508203,0.472917,
0.494427,0.506626,
0.508203,0.472917,
0.491630,0.502379,
0.508203,0.472917,
0.489242,0.497408,
0.508203,0.472917,
0.487320,0.491833,
0.508203,0.472917,
0.485913,0.485792,
0.508203,0.472917,
0.485054,0.479435,
0.508203,0.472917,
0.484766,0.472917,
0.508203,0.472917,
0.485054,0.466399,
0.508203,0.472917,
0.485913,0.460041,
0.508203,0.472917,
0.487320,0.454000,
0.508203,0.472917,
0.489242,0.448426,
0.508203,0.472917,
0.491630,0.443454,
0.508203,0.472917,
0.494427,0.439208,
0.508203,0.472917,
0.497563,0.435791,
0.508203,0.472917,
0.500961,0.433289,
0.508203,0.472917,
0.504537,0.431763,
0.508203,0.472917,
0.508203,0.431250,
0.508203,0.472917,
0.511870,0.431763,
0.508203,0.472917,
0.515446,0.433289,
0.508203,0.472917,
0.518844,0.435791,
0.508203,0.472917,
0.521979,0.439208,
0.508203,0.472917,
0.524776,0.443454,
0.508203,0.472917,
0.527165,0.448426,
0.508203,0.472917,
0.529086,0.454000,
0.508203,0.472917,
0.530494,0.460041,
0.508203,0.472917,
0.531352,0.466399,
0.508203,0.472917,
0.531641,0.472917,
0.531641,0.472917,
0.555078,0.472917,
0.531352,0.479435,
0.554501,0.485953,
0.530494,0.485792,
0.552784,0.498668,
0.529086,0.491833,
0.549969,0.510749,
0.527165,0.497408,
0.546126,0.521899,
0.524776,0.502379,
0.541349,0.531842,
0.521979,0.506626,
0.535756,0.540335,
0.518844,0.510042,
0.529484,0.547167,
0.515446,0.512544,
0.522688,0.552171,
0.511870,0.514070,
0.515536,0.555224,
0.508203,0.514583,
0.508203,0.556250,
0.504537,0.514070,
0.500870,0.555224,
0.500961,0.512544,
0.493718,0.552171,
0.497563,0.510042,
0.486922,0.547167,
0.494427,0.506626,
0.480651,0.540335,
0.491630,0.502379,
0.475058,0.531842,
0.489242,0.497408,
0.470280,0.521899,
0.487320,0.491833,
0.466437,0.510749,
0.485913,0.485792,
0.463622,0.498668,
0.485054,0.479435,
0.461905,0.485953,
0.484766,0.472917,
0.461328,0.472917,
0.485054,0.466399,
0.461905,0.459880,
0.485913,0.460041,
0.463622,0.447165,
0.487320,0.454000,
0.466437,0.435084,
0.489242,0.448426,
0.470280,0.423935,
0.491630,0.443454,
0.475058,0.413991,
0.494427,0.439208,
0.480651,0.405499,
0.497563,0.435791,
0.486922,0.398666,
0.500961,0.433289,
0.493718,0.393662,
0.504537,0.431763,
0.500870,0.390609,
0.508203,0.431250,
0.508203,0.389583,
0.511870,0.431763,
0.515536,0.390609,
0.515446,0.433289,
0.522688,0.393662,
0.518844,0.435791,
0.529484,0.398666,
0.521979,0.439208,
0.535756,0.405499,
0.524776,0.443454,
0.541349,0.413991,
0.527165,0.448426,
0.546126,0.423935,
0.529086,0.454000,
0.549969,0.435084,
0.530494,0.460041,
0.552784,0.447165,
0.531352,0.466399,
0.554501,0.459880,
0.531641,0.472917,
0.555078,0.472917,
0.555078,0.472917,
0.578516,0.472917,
0.554501,0.485953,
0.577650,0.492471,
0.552784,0.498668,
0.575074,0.511544,
0.549969,0.510749,
0.570852,0.529665,
0.546126,0.521899,
0.565087,0.546390,
0.541349,0.531842,
0.557922,0.561305,
0.535756,0.540335,
0.549532,0.574044,
0.529484,0.547167,
0.540124,0.584292,
0.522688,0.552171,
0.529931,0.591799,
0.515536,0.555224,
0.519202,0.596378,
0.508203,0.556250,
0.508203,0.597917,
0.500870,0.555224,
0.497204,0.596378,
0.493718,0.552171,
0.486475,0.591799,
0.486922,0.547167,
0.476282,0.584292,
0.480651,0.540335,
0.466875,0.574044,
0.475058,0.531842,
0.458485,0.561305,
0.470280,0.521899,
0.451319,0.546390,
0.466437,0.510749,
0.445554,0.529665,
0.463622,0.498668,
0.441332,0.511544,
0.461905,0.485953,
0.438756,0.492471,
0.461328,0.472917,
0.437891,0.472917,
0.461905,0.459880,
0.438756,0.453362,
0.463622,0.447165,
0.441332,0.434290,
0.466437,0.435084,
0.445554,0.416168,
0.470280,0.423935,
0.451319,0.399444,
0.475058,0.413991,
0.458485,0.384528,
0.480651,0.405499,
0.466875,0.371790,
0.486922,0.398666,
0.476282,0.361541,
0.493718,0.393662,
0.486475,0.354035,
0.500870,0.390609,
0.497204,0.349456,
0.508203,0.389583,
0.508203,0.347917,
0.515536,0.390609,
0.519202,0.349456,
0.522688,0.393662,
0.529931,0.354035,
0.529484,0.398666,
0.540124,0.361541,
0.535756,0.405499,
0.549532,0.371790,
0.541349,0.413991,
0.557922,0.384528,
0.546126,0.423935,
0.565087,0.399444,
0.549969,0.435084,
0.570852,0.416168,
0.552784,0.447165,
0.575074,0.434290,
0.554501,0.459880,
0.577650,0.453362,
0.555078,0.472917,
0.578516,0.472917,
0.578516,0.472917,
0.601953,0.472917,
0.577650,0.492471,
0.600799,0.498989,
0.575074,0.511544,
0.597365,0.524419,
0.570852,0.529665,
0.591735,0.548582,
0.565087,0.546390,
0.584049,0.570881,
0.557922,0.561305,
0.574494,0.590768,
0.549532,0.574044,
0.563308,0.607753,
0.540124,0.584292,
0.550765,0.621418,
0.529931,0.591799,
0.537174,0.631426,
0.519202,0.596378,
0.522869,0.637531,
0.508203,0.597917,
0.508203,0.639583,
0.497204,0.596378,
0.493537,0.637531,
0.486475,0.591799,
0.479233,0.631426,
0.476282,0.584292,
0.465642,0.621418,
0.466875,0.574044,
0.453098,0.607753,
0.458485,0.561305,
0.441912,0.590768,
0.451319,0.546390,
0.432358,0.570881,
0.445554,0.529665,
0.424671,0.548582,
0.441332,0.511544,
0.419042,0.524419,
0.438756,0.492471,
0.415607,0.498989,
0.437891,0.472917,
0.414453,0.472917,
0.438756,0.453362,
0.415607,0.446844,
0.441332,0.434290,
0.419042,0.421414,
0.445554,0.416168,
0.424671,0.397252,
0.451319,0.399444,
0.432358,0.374952,
0.458485,0.384528,
0.441912,0.355066,
0.466875,0.371790,
0.453098,0.338080,
0.476282,0.361541,
0.465642,0.324416,
0.486475,0.354035,
0.479233,0.314407,
0.497204,0.349456,
0.493537,0.308302,
0.508203,0.347917,
0.508203,0.306250,
0.519202,0.349456,
0.522869,0.308302,
0.529931,0.354035,
0.537174,0.314407,
0.540124,0.361541,
0.550765,0.324416,
0.549532,0.371790,
0.563308,0.338081,
0.557922,0.384528,
0.574494,0.355066,
0.565087,0.399444,
0.584049,0.374952,
0.570852,0.416168,
0.591735,0.397252,
0.575074,0.434290,
0.597365,0.421414,
0.577650,0.453362,
0.600799,0.446844,
0.578516,0.472917,
0.601953,0.472917,
0.601953,0.472917,
0.625391,0.472917,
0.600799,0.498989,
0.623948,0.505507,
0.597365,0.524419,
0.619655,0.537295,
0.591735,0.548582,
0.612618,0.567498,
0.584049,0.570881,
0.603010,0.595372,
0.574494,0.590768,
0.591067,0.620231,
0.563308,0.607753,
0.577084,0.641462,
0.550765,0.621418,
0.561405,0.658543,
0.537174,0.631426,
0.544416,0.671053,
0.522869,0.637531,
0.526535,0.678685,
0.508203,0.639583,
0.508203,0.681250,
0.493537,0.637531,
0.489871,0.678685,
0.479233,0.631426,
0.471990,0.671053,
0.465642,0.621418,
0.455001,0.658543,
0.453098,0.607753,
0.439322,0.641462,
0.441912,0.590768,
0.425339,0.620231,
0.432358,0.570881,
0.413396,0.595372,
0.424671,0.548582,
0.403788,0.567498,
0.419042,0.524419,
0.396751,0.537295,
0.415607,0.498989,
0.392458,0.505507,
0.414453,0.472917,
0.391016,0.472917,
0.415607,0.446844,
0.392458,0.440326,
0.419042,0.421414,
0.396751,0.408538,
0.424671,0.397252,
0.403788,0.378335,
0.432358,0.374952,
0.413396,0.350461,
0.441912,0.355066,
0.425339,0.325603,
0.453098,0.338080,
0.439322,0.304371,
0.465642,0.324416,
0.455001,0.287290,
0.479233,0.314407,
0.471990,0.274780,
0.493537,0.308302,
0.489871,0.267148,
0.508203,0.306250,
0.508203,0.264583,
0.522869,0.308302,
0.526535,0.267148,
0.537174,0.314407,
0.544416,0.274780,
0.550765,0.324416,
0.561405,0.287290,
0.563308,0.338081,
0.577084,0.304371,
0.574494,0.355066,
0.591067,0.325603,
0.584049,0.374952,
0.603010,0.350461,
0.591735,0.397252,
0.612618,0.378335,
0.597365,0.421414,
0.619655,0.408538,
0.600799,0.446844,
0.623948,0.440326,
0.601953,0.472917,
0.625391,0.472917,
0.625391,0.472917,
0.648828,0.472917,
0.623948,0.505507,
0.647097,0.512025,
0.619655,0.537295,
0.641945,0.550171,
0.612618,0.567498,
0.633501,0.586414,
0.603010,0.595372,
0.621971,0.619863,
0.591067,0.620231,
0.607640,0.649693,
0.577084,0.641462,
0.590860,0.675171,
0.561405,0.658543,
0.572046,0.695668,
0.544416,0.671053,
0.551659,0.710681,
0.526535,0.678685,
0.530202,0.719839,
0.508203,0.681250,
0.508203,0.722917,
0.489871,0.678685,
0.486205,0.719839,
0.471990,0.671053,
0.464748,0.710681,
0.455001,0.658543,
0.444361,0.695668,
0.439322,0.641462,
0.425546,0.675171,
0.425339,0.620231,
0.408766,0.649693,
0.413396,0.595372,
0.394435,0.619863,
0.403788,0.567498,
0.382905,0.586414,
0.396751,0.537295,
0.374461,0.550171,
0.392458,0.505507,
0.369309,0.512025,
0.391016,0.472917,
0.367578,0.472917,
0.392458,0.440326,
0.369309,0.433808,
0.396751,0.408538,
0.374461,0.395662,
0.403788,0.378335,
0.382905,0.359419,
0.413396,0.350461,
0.394435,0.325970,
0.425339,0.325603,
0.408766,0.296140,
0.439322,0.304371,
0.425546,0.270662,
0.455001,0.287290,
0.444361,0.250165,
0.471990,0.274780,
0.464748,0.235153,
0.489871,0.267148,
0.486205,0.225995,
0.508203,0.264583,
0.508203,0.222917,
0.526535,0.267148,
0.530202,0.225995,
0.544416,0.274780,
0.551659,0.235153,
0.561405,0.287290,
0.572046,0.250165,
0.577084,0.304371,
0.590860,0.270662,
0.591067,0.325603,
0.607640,0.296140,
0.603010,0.350461,
0.621971,0.325970,
0.612618,0.378335,
0.633501,0.359419,
0.619655,0.408538,
0.641945,0.395662,
0.623948,0.440326,
0.647097,0.433808,
0.625391,0.472917,
0.648828,0.472917,
0.648828,0.472917,
0.672266,0.472917,
0.647097,0.512025,
0.670246,0.518543,
0.641945,0.550171,
0.664236,0.563047,
0.633501,0.586414,
0.654384,0.605331,
0.621971,0.619863,
0.640933,0.644354,
0.607640,0.649693,
0.624213,0.679156,
0.590860,0.675171,
0.604637,0.708880,
0.572046,0.695668,
0.582686,0.732794,
0.551659,0.710681,
0.558901,0.750308,
0.530202,0.719839,
0.533868,0.760993,
0.508203,0.722917,
0.508203,0.764583,
0.486205,0.719839,
0.482538,0.760993,
0.464748,0.710681,
0.457505,0.750308,
0.444361,0.695668,
0.433720,0.732794,
0.425546,0.675171,
0.411770,0.708880,
0.408766,0.649693,
0.392193,0.679156,
0.394435,0.619863,
0.375474,0.644354,
0.382905,0.586414,
0.362022,0.605331,
0.374461,0.550171,
0.352170,0.563047,
0.369309,0.512025,
0.346161,0.518543,
0.367578,0.472917,
0.344141,0.472917,
0.369309,0.433808,
0.346161,0.427290,
0.374461,0.395662,
0.352170,0.382787,
0.382905,0.359419,
0.362022,0.340503,
0.394435,0.325970,
0.375474,0.301479,
0.408766,0.296140,
0.392193,0.266677,
0.425546,0.270662,
0.411770,0.236953,
0.444361,0.250165,
0.433720,0.213040,
0.464748,0.235152,
0.457505,0.195525,
0.486205,0.225995,
0.482538,0.184841,
0.508203,0.222917,
0.508203,0.181250,
0.530202,0.225995,
0.533868,0.184841,
0.551659,0.235152,
0.558901,0.195525,
0.572046,0.250165,
0.582686,0.213040,
0.590860,0.270662,
0.604637,0.236953,
0.607640,0.296140,
0.624213,0.266677,
0.621971,0.325970,
0.640933,0.301479,
0.633501,0.359419,
0.654384,0.340503,
0.641945,0.395662,
0.664236,0.382787,
0.647097,0.433808,
0.670246,0.427290,
0.648828,0.472917,
0.672266,0.472917,
0.672266,0.472917,
0.695703,0.472917,
0.670246,0.518543,
0.693395,0.525061,
0.664236,0.563047,
0.686526,0.575922,
0.654384,0.605331,
0.675267,0.624247,
0.640933,0.644354,
0.659894,0.668845,
0.624213,0.679156,
0.640786,0.708619,
0.604637,0.708880,
0.618413,0.742589,
0.582686,0.732794,
0.593326,0.769919,
0.558901,0.750308,
0.566144,0.789936,
0.533868,0.760992,
0.537535,0.802146,
0.508203,0.764583,
0.508203,0.806250,
0.482538,0.760992,
0.478872,0.802146,
0.457505,0.750308,
0.450262,0.789935,
0.433720,0.732794,
0.423080,0.769919,
0.411770,0.708880,
0.397993,0.742589,
0.392193,0.679156,
0.375621,0.708619,
0.375474,0.644354,
0.356512,0.668845,
0.362022,0.605331,
0.341139,0.624247,
0.352170,0.563047,
0.329880,0.575922,
0.346161,0.518543,
0.323012,0.525061,
0.344141,0.472917,
0.320703,0.472917,
0.346161,0.427290,
0.323012,0.420772,
0.352170,0.382787,
0.329880,0.369911,
0.362022,0.340503,
0.341139,0.321586,
0.375474,0.301479,
0.356512,0.276988,
0.392193,0.266677,
0.375621,0.237214,
0.411770,0.236953,
0.397993,0.203244,
0.433720,0.213040,
0.423080,0.175914,
0.457505,0.195525,
0.450262,0.155898,
0.482538,0.184841,
0.478872,0.143687,
0.508203,0.181250,
0.508203,0.139583,
0.533868,0.184841,
0.537535,0.143687,
0.558901,0.195525,
0.566144,0.155898,
0.582686,0.213040,
0.593326,0.175914,
0.604637,0.236953,
0.618413,0.203244,
0.624213,0.266677,
0.640786,0.237214,
0.640933,0.301479,
0.659894,0.276988,
0.654384,0.340503,
0.675267,0.321586,
0.664236,0.382787,
0.686526,0.369911,
0.670246,0.427290,
0.693395,0.420772,
0.672266,0.472917,
0.695703,0.472917,
0.695703,0.472917,
0.719141,0.472917,
0.693395,0.525061,
0.716544,0.531580,
0.686526,0.575922,
0.708817,0.588798,
0.675267,0.624247,
0.696150,0.643163,
0.659894,0.668845,
0.678855,0.693336,
0.640786,0.708619,
0.657359,0.738082,
0.618413,0.742589,
0.632189,0.776298,
0.593326,0.769919,
0.603967,0.807044,
0.566144,0.789936,
0.573386,0.829563,
0.537535,0.802146,
0.541201,0.843300,
0.508203,0.806250,
0.508203,0.847917,
0.478872,0.802146,
0.475205,0.843300,
0.450262,0.789935,
0.443020,0.829563,
0.423080,0.769919,
0.412439,0.807044,
0.397993,0.742589,
0.384217,0.776298,
0.375621,0.708619,
0.359048,0.738082,
0.356512,0.668845,
0.337551,0.693336,
0.341139,0.624247,
0.320256,0.643163,
0.329880,0.575922,
0.307590,0.588798,
0.323012,0.525061,
0.299863,0.531580,
0.320703,0.472917,
0.297266,0.472917,
0.323012,0.420772,
0.299863,0.414254,
0.329880,0.369911,
0.307590,0.357035,
0.341139,0.321586,
0.320256,0.302670,
0.356512,0.276988,
0.337551,0.252497,
0.375621,0.237214,
0.359048,0.207752,
0.397993,0.203244,
0.384217,0.169535,
0.423080,0.175914,
0.412440,0.138789,
0.450262,0.155898,
0.443020,0.116270,
0.478872,0.143687,
0.475205,0.102534,
0.508203,0.139583,
0.508203,0.097917,
0.537535,0.143687,
0.541201,0.102534,
0.566144,0.155898,
0.573386,0.116270,
0.593326,0.175914,
0.603967,0.138789,
0.618413,0.203244,
0.632189,0.169535,
0.640786,0.237214,
0.657359,0.207752,
0.659894,0.276988,
0.678855,0.252497,
0.675267,0.321586,
0.696150,0.302670,
0.686526,0.369911,
0.708817,0.357035,
0.693395,0.420772,
0.716544,0.414254,
0.695703,0.472917,
0.719141,0.472917,
0.719141,0.472917,
0.742578,0.472917,
0.716544,0.531580,
0.739693,0.538098,
0.708817,0.588798,
0.731107,0.601674,
0.696150,0.643163,
0.717033,0.662079,
0.678855,0.693336,
0.697816,0.717827,
0.657359,0.738082,
0.673931,0.767545,
0.632189,0.776298,
0.645965,0.810007,
0.603967,0.807044,
0.614607,0.844169,
0.573386,0.829563,
0.580629,0.869190,
0.541201,0.843300,
0.544867,0.884454,
0.508203,0.847917,
0.508203,0.889583,
0.475205,0.843300,
0.471539,0.884454,
0.443020,0.829563,
0.435777,0.869190,
0.412439,0.807044,
0.401799,0.844169,
0.384217,0.776298,
0.370441,0.810007,
0.359048,0.738082,
0.342475,0.767545,
0.337551,0.693336,
0.318590,0.717827,
0.320256,0.643163,
0.299373,0.662079,
0.307590,0.588798,
0.285299,0.601674,
0.299863,0.531580,
0.276714,0.538098,
0.297266,0.472917,
0.273828,0.472917,
0.299863,0.414254,
0.276714,0.407736,
0.307590,0.357035,
0.285299,0.344160,
0.320256,0.302670,
0.299373,0.283754,
0.337551,0.252497,
0.318590,0.228006,
0.359048,0.207752,
0.342475,0.178289,
0.384217,0.169535,
0.370441,0.135826,
0.412440,0.138789,
0.401799,0.101664,
0.443020,0.116270,
0.435777,0.076643,
0.475205,0.102534,
0.471539,0.061380,
0.508203,0.097917,
0.508203,0.056250,
0.541201,0.102534,
0.544868,0.061380,
0.573386,0.116270,
0.580629,0.076643,
0.603967,0.138789,
0.614607,0.101664,
0.632189,0.169535,
0.645965,0.135826,
0.657359,0.207752,
0.673931,0.178289,
0.678855,0.252497,
0.697817,0.228006,
0.696150,0.302670,
0.717033,0.283754,
0.708817,0.357035,
0.731107,0.344160,
0.716544,0.414254,
0.739693,0.407736,
0.719141,0.472917,
0.742578,0.472917,
0.250391,0.500000,
0.250391,0.550000,
0.244131,0.500000,
0.244131,0.550000,
0.237871,0.500000,
0.237871,0.550000,
0.231611,0.500000,
0.231611,0.550000,
0.225352,0.500000,
0.225352,0.550000,
0.219092,0.500000,
0.219092,0.550000,
0.212832,0.500000,
0.212832,0.550000,
0.206572,0.500000,
0.206572,0.550000,
0.200312,0.500000,
0.200312,0.550000,
0.194053,0.500000,
0.194053,0.550000,
0.187793,0.500000,
0.187793,0.550000,
0.181533,0.500000,
0.181533,0.550000,
0.175273,0.500000,
0.175273,0.550000,
0.169014,0.500000,
0.169014,0.550000,
0.162754,0.500000,
0.162754,0.550000,
0.156494,0.500000,
0.156494,0.550000,
0.150234,0.500000,
0.150234,0.550000,
0.143975,0.500000,
0.143975,0.550000,
0.137715,0.500000,
0.137715,0.550000,
0.131455,0.500000,
0.131455,0.550000,
0.125195,0.500000,
0.125195,0.550000,
0.118936,0.500000,
0.118936,0.550000,
0.112676,0.500000,
0.112676,0.550000,
0.106416,0.500000,
0.106416,0.550000,
0.100156,0.500000,
0.100156,0.550000,
0.093896,0.500000,
0.093896,0.550000,
0.087637,0.500000,
0.087637,0.550000,
0.081377,0.500000,
0.081377,0.550000,
0.075117,0.500000,
0.075117,0.550000,
0.068857,0.500000,
0.068857,0.550000,
0.062598,0.500000,
0.062598,0.550000,
0.056338,0.500000,
0.056338,0.550000,
0.050078,0.500000,
0.050078,0.550000,
0.043818,0.500000,
0.043818,0.550000,
0.037559,0.500000,
0.037559,0.550000,
0.031299,0.500000,
0.031299,0.550000,
0.025039,0.500000,
0.025039,0.550000,
0.018779,0.500000,
0.018779,0.550000,
0.012520,0.500000,
0.012520,0.550000,
0.006260,0.500000,
0.006260,0.550000,
0.000000,0.500000,
0.000000,0.550000,
0.250391,0.550000,
0.250391,0.600000,
0.244131,0.550000,
0.244131,0.600000,
0.237871,0.550000,
0.237871,0.600000,
0.231611,0.550000,
0.231611,0.600000,
0.225352,0.550000,
0.225352,0.600000,
0.219092,0.550000,
0.219092,0.600000,
0.212832,0.550000,
0.212832,0.600000,
0.206572,0.550000,
0.206572,0.600000,
0.200312,0.550000,
0.200312,0.600000,
0.194053,0.550000,
0.194053,0.600000,
0.187793,0.550000,
0.187793,0.600000,
0.181533,0.550000,
0.181533,0.600000,
0.175273,0.550000,
0.175273,0.600000,
0.169014,0.550000,
0.169014,0.600000,
0.162754,0.550000,
0.162754,0.600000,
0.156494,0.550000,
0.156494,0.600000,
0.150234,0.550000,
0.150234,0.600000,
0.143975,0.550000,
0.143975,0.600000,
0.137715,0.550000,
0.137715,0.600000,
0.131455,0.550000,
0.131455,0.600000,
0.125195,0.550000,
0.125195,0.600000,
0.118936,0.550000,
0.118936,0.600000,
0.112676,0.550000,
0.112676,0.600000,
0.106416,0.550000,
0.106416,0.600000,
0.100156,0.550000,
0.100156,0.600000,
0.093896,0.550000,
0.093896,0.600000,
0.087637,0.550000,
0.087637,0.600000,
0.081377,0.550000,
0.081377,0.600000,
0.075117,0.550000,
0.075117,0.600000,
0.068857,0.550000,
0.068857,0.600000,
0.062598,0.550000,
0.062598,0.600000,
0.056338,0.550000,
0.056338,0.600000,
0.050078,0.550000,
0.050078,0.600000,
0.043818,0.550000,
0.043818,0.600000,
0.037559,0.550000,
0.037559,0.600000,
0.031299,0.550000,
0.031299,0.600000,
0.025039,0.550000,
0.025039,0.600000,
0.018779,0.550000,
0.018779,0.600000,
0.012520,0.550000,
0.012520,0.600000,
0.006260,0.550000,
0.006260,0.600000,
0.000000,0.550000,
0.000000,0.600000,
0.250391,0.600000,
0.250391,0.650000,
0.244131,0.600000,
0.244131,0.650000,
0.237871,0.600000,
0.237871,0.650000,
0.231611,0.600000,
0.231611,0.650000,
0.225352,0.600000,
0.225352,0.650000,
0.219092,0.600000,
0.219092,0.650000,
0.212832,0.600000,
0.212832,0.650000,
0.206572,0.600000,
0.206572,0.650000,
0.200312,0.600000,
0.200312,0.650000,
0.194053,0.600000,
0.194053,0.650000,
0.187793,0.600000,
0.187793,0.650000,
0.181533,0.600000,
0.181533,0.650000,
0.175273,0.600000,
0.175273,0.650000,
0.169014,0.600000,
0.169014,0.650000,
0.162754,0.600000,
0.162754,0.650000,
0.156494,0.600000,
0.156494,0.650000,
0.150234,0.600000,
0.150234,0.650000,
0.143975,0.600000,
0.143975,0.650000,
0.137715,0.600000,
0.137715,0.650000,
0.131455,0.600000,
0.131455,0.650000,
0.125195,0.600000,
0.125195,0.650000,
0.118936,0.600000,
0.118936,0.650000,
0.112676,0.600000,
0.112676,0.650000,
0.106416,0.600000,
0.106416,0.650000,
0.100156,0.600000,
0.100156,0.650000,
0.093896,0.600000,
0.093896,0.650000,
0.087637,0.600000,
0.087637,0.650000,
0.081377,0.600000,
0.081377,0.650000,
0.075117,0.600000,
0.075117,0.650000,
0.068857,0.600000,
0.068857,0.650000,
0.062598,0.600000,
0.062598,0.650000,
0.056338,0.600000,
0.056338,0.650000,
0.050078,0.600000,
0.050078,0.650000,
0.043818,0.600000,
0.043818,0.650000,
0.037559,0.600000,
0.037559,0.650000,
0.031299,0.600000,
0.031299,0.650000,
0.025039,0.600000,
0.025039,0.650000,
0.018779,0.600000,
0.018779,0.650000,
0.012520,0.600000,
0.012520,0.650000,
0.006260,0.600000,
0.006260,0.650000,
0.000000,0.600000,
0.000000,0.650000,
0.250391,0.650000,
0.250391,0.700000,
0.244131,0.650000,
0.244131,0.700000,
0.237871,0.650000,
0.237871,0.700000,
0.231611,0.650000,
0.231611,0.700000,
0.225352,0.650000,
0.225352,0.700000,
0.219092,0.650000,
0.219092,0.700000,
0.212832,0.650000,
0.212832,0.700000,
0.206572,0.650000,
0.206572,0.700000,
0.200312,0.650000,
0.200312,0.700000,
0.194053,0.650000,
0.194053,0.700000,
0.187793,0.650000,
0.187793,0.700000,
0.181533,0.650000,
0.181533,0.700000,
0.175273,0.650000,
0.175273,0.700000,
0.169014,0.650000,
0.169014,0.700000,
0.162754,0.650000,
0.162754,0.700000,
0.156494,0.650000,
0.156494,0.700000,
0.150234,0.650000,
0.150234,0.700000,
0.143975,0.650000,
0.143975,0.700000,
0.137715,0.650000,
0.137715,0.700000,
0.131455,0.650000,
0.131455,0.700000,
0.125195,0.650000,
0.125195,0.700000,
0.118936,0.650000,
0.118936,0.700000,
0.112676,0.650000,
0.112676,0.700000,
0.106416,0.650000,
0.106416,0.700000,
0.100156,0.650000,
0.100156,0.700000,
0.093896,0.650000,
0.093896,0.700000,
0.087637,0.650000,
0.087637,0.700000,
0.081377,0.650000,
0.081377,0.700000,
0.075117,0.650000,
0.075117,0.700000,
0.068857,0.650000,
0.068857,0.700000,
0.062598,0.650000,
0.062598,0.700000,
0.056338,0.650000,
0.056338,0.700000,
0.050078,0.650000,
0.050078,0.700000,
0.043818,0.650000,
0.043818,0.700000,
0.037559,0.650000,
0.037559,0.700000,
0.031299,0.650000,
0.031299,0.700000,
0.025039,0.650000,
0.025039,0.700000,
0.018779,0.650000,
0.018779,0.700000,
0.012520,0.650000,
0.012520,0.700000,
0.006260,0.650000,
0.006260,0.700000,
0.000000,0.650000,
0.000000,0.700000,
0.250391,0.700000,
0.250391,0.750000,
0.244131,0.700000,
0.244131,0.750000,
0.237871,0.700000,
0.237871,0.750000,
0.231611,0.700000,
0.231611,0.750000,
0.225352,0.700000,
0.225352,0.750000,
0.219092,0.700000,
0.219092,0.750000,
0.212832,0.700000,
0.212832,0.750000,
0.206572,0.700000,
0.206572,0.750000,
0.200312,0.700000,
0.200312,0.750000,
0.194053,0.700000,
0.194053,0.750000,
0.187793,0.700000,
0.187793,0.750000,
0.181533,0.700000,
0.181533,0.750000,
0.175273,0.700000,
0.175273,0.750000,
0.169014,0.700000,
0.169014,0.750000,
0.162754,0.700000,
0.162754,0.750000,
0.156494,0.700000,
0.156494,0.750000,
0.150234,0.700000,
0.150234,0.750000,
0.143975,0.700000,
0.143975,0.750000,
0.137715,0.700000,
0.137715,0.750000,
0.131455,0.700000,
0.131455,0.750000,
0.125195,0.700000,
0.125195,0.750000,
0.118936,0.700000,
0.118936,0.750000,
0.112676,0.700000,
0.112676,0.750000,
0.106416,0.700000,
0.106416,0.750000,
0.100156,0.700000,
0.100156,0.750000,
0.093896,0.700000,
0.093896,0.750000,
0.087637,0.700000,
0.087637,0.750000,
0.081377,0.700000,
0.081377,0.750000,
0.075117,0.700000,
0.075117,0.750000,
0.068857,0.700000,
0.068857,0.750000,
0.062598,0.700000,
0.062598,0.750000,
0.056338,0.700000,
0.056338,0.750000,
0.050078,0.700000,
0.050078,0.750000,
0.043818,0.700000,
0.043818,0.750000,
0.037559,0.700000,
0.037559,0.750000,
0.031299,0.700000,
0.031299,0.750000,
0.025039,0.700000,
0.025039,0.750000,
0.018779,0.700000,
0.018779,0.750000,
0.012520,0.700000,
0.012520,0.750000,
0.006260,0.700000,
0.006260,0.750000,
0.000000,0.700000,
0.000000,0.750000,
0.250391,0.750000,
0.250391,0.800000,
0.244131,0.750000,
0.244131,0.800000,
0.237871,0.750000,
0.237871,0.800000,
0.231611,0.750000,
0.231611,0.800000,
0.225352,0.750000,
0.225352,0.800000,
0.219092,0.750000,
0.219092,0.800000,
0.212832,0.750000,
0.212832,0.800000,
0.206572,0.750000,
0.206572,0.800000,
0.200312,0.750000,
0.200312,0.800000,
0.194053,0.750000,
0.194053,0.800000,
0.187793,0.750000,
0.187793,0.800000,
0.181533,0.750000,
0.181533,0.800000,
0.175273,0.750000,
0.175273,0.800000,
0.169014,0.750000,
0.169014,0.800000,
0.162754,0.750000,
0.162754,0.800000,
0.156494,0.750000,
0.156494,0.800000,
0.150234,0.750000,
0.150234,0.800000,
0.143975,0.750000,
0.143975,0.800000,
0.137715,0.750000,
0.137715,0.800000,
0.131455,0.750000,
0.131455,0.800000,
0.125195,0.750000,
0.125195,0.800000,
0.118936,0.750000,
0.118936,0.800000,
0.112676,0.750000,
0.112676,0.800000,
0.106416,0.750000,
0.106416,0.800000,
0.100156,0.750000,
0.100156,0.800000,
0.093896,0.750000,
0.093896,0.800000,
0.087637,0.750000,
0.087637,0.800000,
0.081377,0.750000,
0.081377,0.800000,
0.075117,0.750000,
0.075117,0.800000,
0.068857,0.750000,
0.068857,0.800000,
0.062598,0.750000,
0.062598,0.800000,
0.056338,0.750000,
0.056338,0.800000,
0.050078,0.750000,
0.050078,0.800000,
0.043818,0.750000,
0.043818,0.800000,
0.037559,0.750000,
0.037559,0.800000,
0.031299,0.750000,
0.031299,0.800000,
0.025039,0.750000,
0.025039,0.800000,
0.018779,0.750000,
0.018779,0.800000,
0.012520,0.750000,
0.012520,0.800000,
0.006260,0.750000,
0.006260,0.800000,
0.000000,0.750000,
0.000000,0.800000,
0.250391,0.800000,
0.250391,0.850000,
0.244131,0.800000,
0.244131,0.850000,
0.237871,0.800000,
0.237871,0.850000,
0.231611,0.800000,
0.231611,0.850000,
0.225352,0.800000,
0.225352,0.850000,
0.219092,0.800000,
0.219092,0.850000,
0.212832,0.800000,
0.212832,0.850000,
0.206572,0.800000,
0.206572,0.850000,
0.200312,0.800000,
0.200312,0.850000,
0.194053,0.800000,
0.194053,0.850000,
0.187793,0.800000,
0.187793,0.850000,
0.181533,0.800000,
0.181533,0.850000,
0.175273,0.800000,
0.175273,0.850000,
0.169014,0.800000,
0.169014,0.850000,
0.162754,0.800000,
0.162754,0.850000,
0.156494,0.800000,
0.156494,0.850000,
0.150234,0.800000,
0.150234,0.850000,
0.143975,0.800000,
0.143975,0.850000,
0.137715,0.800000,
0.137715,0.850000,
0.131455,0.800000,
0.131455,0.850000,
0.125195,0.800000,
0.125195,0.850000,
0.118936,0.800000,
0.118936,0.850000,
0.112676,0.800000,
0.112676,0.850000,
0.106416,0.800000,
0.106416,0.850000,
0.100156,0.800000,
0.100156,0.850000,
0.093896,0.800000,
0.093896,0.850000,
0.087637,0.800000,
0.087637,0.850000,
0.081377,0.800000,
0.081377,0.850000,
0.075117,0.800000,
0.075117,0.850000,
0.068857,0.800000,
0.068857,0.850000,
0.062598,0.800000,
0.062598,0.850000,
0.056338,0.800000,
0.056338,0.850000,
0.050078,0.800000,
0.050078,0.850000,
0.043818,0.800000,
0.043818,0.850000,
0.037559,0.800000,
0.037559,0.850000,
0.031299,0.800000,
0.031299,0.850000,
0.025039,0.800000,
0.025039,0.850000,
0.018779,0.800000,
0.018779,0.850000,
0.012520,0.800000,
0.012520,0.850000,
0.006260,0.800000,
0.006260,0.850000,
0.000000,0.800000,
0.000000,0.850000,
0.250391,0.850000,
0.250391,0.900000,
0.244131,0.850000,
0.244131,0.900000,
0.237871,0.850000,
0.237871,0.900000,
0.231611,0.850000,
0.231611,0.900000,
0.225352,0.850000,
0.225352,0.900000,
0.219092,0.850000,
0.219092,0.900000,
0.212832,0.850000,
0.212832,0.900000,
0.206572,0.850000,
0.206572,0.900000,
0.200312,0.850000,
0.200312,0.900000,
0.194053,0.850000,
0.194053,0.900000,
0.187793,0.850000,
0.187793,0.900000,
0.181533,0.850000,
0.181533,0.900000,
0.175273,0.850000,
0.175273,0.900000,
0.169014,0.850000,
0.169014,0.900000,
0.162754,0.850000,
0.162754,0.900000,
0.156494,0.850000,
0.156494,0.900000,
0.150234,0.850000,
0.150234,0.900000,
0.143975,0.850000,
0.143975,0.900000,
0.137715,0.850000,
0.137715,0.900000,
0.131455,0.850000,
0.131455,0.900000,
0.125195,0.850000,
0.125195,0.900000,
0.118936,0.850000,
0.118936,0.900000,
0.112676,0.850000,
0.112676,0.900000,
0.106416,0.850000,
0.106416,0.900000,
0.100156,0.850000,
0.100156,0.900000,
0.093896,0.850000,
0.093896,0.900000,
0.087637,0.850000,
0.087637,0.900000,
0.081377,0.850000,
0.081377,0.900000,
0.075117,0.850000,
0.075117,0.900000,
0.068857,0.850000,
0.068857,0.900000,
0.062598,0.850000,
0.062598,0.900000,
0.056338,0.850000,
0.056338,0.900000,
0.050078,0.850000,
0.050078,0.900000,
0.043818,0.850000,
0.043818,0.900000,
0.037559,0.850000,
0.037559,0.900000,
0.031299,0.850000,
0.031299,0.900000,
0.025039,0.850000,
0.025039,0.900000,
0.018779,0.850000,
0.018779,0.900000,
0.012520,0.850000,
0.012520,0.900000,
0.006260,0.850000,
0.006260,0.900000,
0.000000,0.850000,
0.000000,0.900000,
0.250391,0.900000,
0.250391,0.950000,
0.244131,0.900000,
0.244131,0.950000,
0.237871,0.900000,
0.237871,0.950000,
0.231611,0.900000,
0.231611,0.950000,
0.225352,0.900000,
0.225352,0.950000,
0.219092,0.900000,
0.219092,0.950000,
0.212832,0.900000,
0.212832,0.950000,
0.206572,0.900000,
0.206572,0.950000,
0.200312,0.900000,
0.200312,0.950000,
0.194053,0.900000,
0.194053,0.950000,
0.187793,0.900000,
0.187793,0.950000,
0.181533,0.900000,
0.181533,0.950000,
0.175273,0.900000,
0.175273,0.950000,
0.169014,0.900000,
0.169014,0.950000,
0.162754,0.900000,
0.162754,0.950000,
0.156494,0.900000,
0.156494,0.950000,
0.150234,0.900000,
0.150234,0.950000,
0.143975,0.900000,
0.143975,0.950000,
0.137715,0.900000,
0.137715,0.950000,
0.131455,0.900000,
0.131455,0.950000,
0.125195,0.900000,
0.125195,0.950000,
0.118936,0.900000,
0.118936,0.950000,
0.112676,0.900000,
0.112676,0.950000,
0.106416,0.900000,
0.106416,0.950000,
0.100156,0.900000,
0.100156,0.950000,
0.093896,0.900000,
0.093896,0.950000,
0.087637,0.900000,
0.087637,0.950000,
0.081377,0.900000,
0.081377,0.950000,
0.075117,0.900000,
0.075117,0.950000,
0.068857,0.900000,
0.068857,0.950000,
0.062598,0.900000,
0.062598,0.950000,
0.056338,0.900000,
0.056338,0.950000,
0.050078,0.900000,
0.050078,0.950000,
0.043818,0.900000,
0.043818,0.950000,
0.037559,0.900000,
0.037559,0.950000,
0.031299,0.900000,
0.031299,0.950000,
0.025039,0.900000,
0.025039,0.950000,
0.018779,0.900000,
0.018779,0.950000,
0.012520,0.900000,
0.012520,0.950000,
0.006260,0.900000,
0.006260,0.950000,
0.000000,0.900000,
0.000000,0.950000,
0.250391,0.950000,
0.250391,1.000000,
0.244131,0.950000,
0.244131,1.000000,
0.237871,0.950000,
0.237871,1.000000,
0.231611,0.950000,
0.231611,1.000000,
0.225352,0.950000,
0.225352,1.000000,
0.219092,0.950000,
0.219092,1.000000,
0.212832,0.950000,
0.212832,1.000000,
0.206572,0.950000,
0.206572,1.000000,
0.200312,0.950000,
0.200312,1.000000,
0.194053,0.950000,
0.194053,1.000000,
0.187793,0.950000,
0.187793,1.000000,
0.181533,0.950000,
0.181533,1.000000,
0.175273,0.950000,
0.175273,1.000000,
0.169014,0.950000,
0.169014,1.000000,
0.162754,0.950000,
0.162754,1.000000,
0.156494,0.950000,
0.156494,1.000000,
0.150234,0.950000,
0.150234,1.000000,
0.143975,0.950000,
0.143975,1.000000,
0.137715,0.950000,
0.137715,1.000000,
0.131455,0.950000,
0.131455,1.000000,
0.125195,0.950000,
0.125195,1.000000,
0.118936,0.950000,
0.118936,1.000000,
0.112676,0.950000,
0.112676,1.000000,
0.106416,0.950000,
0.106416,1.000000,
0.100156,0.950000,
0.100156,1.000000,
0.093896,0.950000,
0.093896,1.000000,
0.087637,0.950000,
0.087637,1.000000,
0.081377,0.950000,
0.081377,1.000000,
0.075117,0.950000,
0.075117,1.000000,
0.068857,0.950000,
0.068857,1.000000,
0.062598,0.950000,
0.062598,1.000000,
0.056338,0.950000,
0.056338,1.000000,
0.050078,0.950000,
0.050078,1.000000,
0.043818,0.950000,
0.043818,1.000000,
0.037559,0.950000,
0.037559,1.000000,
0.031299,0.950000,
0.031299,1.000000,
0.025039,0.950000,
0.025039,1.000000,
0.018779,0.950000,
0.018779,1.000000,
0.012520,0.950000,
0.012520,1.000000,
0.006260,0.950000,
0.006260,1.000000,
0.000000,0.950000,
0.000000,1.000000 };
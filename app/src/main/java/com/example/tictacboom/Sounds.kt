package com.example.tictacboom

enum class Sounds(val sound: Int,  val soundId: Int) {
    BOMB_1_SOUND(R.raw.bomb_1_sound,1),
    CPU_POINT_SOUND(R.raw.cpu_point_sound,2),
    PIECES_FLIP_OFF_BOARD(R.raw.pieces_flip_off_board,3),
    PLAYER_POINT_SOUND(R.raw.player_point_sound,4),
    SET_SOUND(R.raw.set_sound,5),
    TIC_TAC_BOOM_INTRO(R.raw.tic_tac_boom_intro,6),
    WINNER_CELEBRATE_SOUND(R.raw.winner_celebrate_sound,7),
    WINNING_LINE_SOUND(R.raw.winning_line_sound,8)
}
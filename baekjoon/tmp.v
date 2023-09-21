module project(clock_50m, pb, fnd_s, fnd_d);


    input clock_50m;

    ///// keypad input /////
    input [15:0] pb;

    /////  8-bits for 7-segment /////
    output reg [7:0] fnd_d;

    ///// six 7-segment display address /////
    output reg [5:0] fnd_s;

    ///// clock /////
    reg [31:0] init_counter;
    reg [15:0] npb;
    reg [2:0] fnd_cnt;
    reg fnd_clk;
    reg sw_clk;

    ///// 7-segment /////
    reg [6:0] set_no1;
    reg [6:0] set_no2;
    reg [6:0] set_no3;
    reg [6:0] set_no4;
    reg [6:0] set_no5;
    reg [6:0] set_no6;

    reg [6:0] seg_100000;
    reg [6:0] seg_10000;
    reg [6:0] seg_1000;
    reg [6:0] seg_100;
    reg [6:0] seg_10;
    reg [6:0] seg_1;

    ///// switch(keypad) control /////
    reg [15:0] pb_1st;
    reg [15:0] pb_2nd;
    reg sw_toggle;

    ///// sw_status /////
    reg [3:0] sw_status;
    parameter sw_idle = 0;
    parameter sw_s1 = 1;
    parameter sw_s2 = 2;
    parameter sw_s3 = 3;
    parameter sw_s4 = 4;
    parameter sw_s5 = 5;
    parameter sw_s6 = 6;
    parameter sw_error = 7;

    ///// operator /////
    integer input_num;
    integer store_num;
    integer error;
    integer hsd;

    reg[40:0] op_status;
    reg[40:0] display;

    reg[40:0] store_op;
    reg[40:0] input_op;


    ///// initial value setting /////
    initial begin

        sw_status <= sw_idle;
        sw_toggle <= 0;
        npb <= 'h0000;
        pb_1st <= 'h0000;
        pb_2nd <= 'h0000;

        ///// Initialize display '------' /////
        set_no1 <= 11;
        set_no2 <= 11;
        set_no3 <= 11;
        set_no4 <= 11;
        set_no5 <= 11;
        set_no6 <= 11;

    end


    ///// clock_50m; clock counter /////
    always @(posedge clock_50m) begin

        init_counter <= init_counter + 1;

    end


    ///// input; clock divider /////
    always begin

        ///// 16-keypads input /////
        npb <= ~pb;

        ///// clock for keypad(switch) /////
        sw_clk <= init_counter[20];

        ///// clock for 7-segment /////
        fnd_clk <= init_counter[16];

    end


    ///// sw_clk; /////
    always @(posedge sw_clk) begin

        pb_2nd <= pb_1st;
        pb_1st <= npb;

        ///// get two consecutive inputs to correct switch(keypad) error /////
        if (pb_2nd == 'h0000 && pb_1st != pb_2nd) begin

            sw_toggle <= 1;

        end

        ///// keypad input /////
        if (sw_toggle == 1 && pb_1st == pb_2nd) begin

            sw_toggle <= 0;

            case (pb_1st)

                ///// 3rd row /////

                'h0100: begin ///// 1 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=1;
                            input_num<=1;

                        end


                        sw_error: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=1;
                            input_num<=1;

                        end


                        sw_s1: begin

                            sw_status <= sw_s2;
                            set_no5<=set_no6;
                            set_no6<=1;

                            if(set_no6==11)begin

                                input_num<=-1;

                            end

                            else begin

                                input_num <= 10*set_no6+1;

                            end

                        end


                        sw_s2: begin

                            sw_status <= sw_s3;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=1;

                            if(set_no5==11)begin

                                input_num<=-(10*set_no6+1);

                            end

                            else begin

                                input_num <= 100*set_no5+10*set_no6+1;

                            end

                        end


                        sw_s3: begin

                            sw_status <= sw_s4;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=1;

                            if(set_no4==11)begin

                                input_num<=-(100*set_no5+10*set_no6+1);

                            end

                            else begin

                                input_num <= 1000*set_no4+100*set_no5+10*set_no6+1;

                            end

                        end


                        sw_s4: begin

                            sw_status <= sw_s5;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=1;

                            if(set_no3==11)begin

                                input_num<=-(1000*set_no4+100*set_no5+10*set_no6+1);

                            end

                            else begin

                                input_num <= 10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+1;

                            end

                        end


                        sw_s5: begin

                            sw_status <= sw_s6;
                            set_no1<=set_no2;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=1;

                            if(set_no3==11)begin

                                input_num<=-(10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+1);

                            end

                            else begin

                                input_num <= 100000*set_no2+10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+1;

                            end

                        end


                        sw_s6: begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end

                'h0200: begin ///// 2 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=2;
                            input_num<=2;

                        end

                        sw_error: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=2;
                            input_num<=2;

                        end

                        sw_s1: begin

                            sw_status <= sw_s2;
                            set_no5<=set_no6;
                            set_no6<=2;

                            if(set_no6==11)begin

                                input_num<=-2;

                            end

                            else begin

                                input_num <= 10*set_no6+2;

                            end

                        end

                        sw_s2: begin

                            sw_status <= sw_s3;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=2;

                            if(set_no5==11)begin

                                input_num<=-(10*set_no6+2);

                            end

                            else begin

                                input_num <= 100*set_no5+10*set_no6+2;

                            end

                        end

                        sw_s3: begin

                            sw_status <= sw_s4;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=2;

                            if(set_no4==11)begin

                                input_num<=-(100*set_no5+10*set_no6+2);

                            end

                            else begin

                                input_num <= 1000*set_no4+100*set_no5+10*set_no6+2;

                            end

                        end

                        sw_s4: begin

                            sw_status <= sw_s5;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=2;

                            if(set_no3==11)begin

                                input_num<=-(1000*set_no4+100*set_no5+10*set_no6+2);

                            end

                            else begin

                                input_num <= 10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+2;

                            end

                        end

                        sw_s5: begin

                            sw_status <= sw_s6;
                            set_no1<=set_no2;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=2;

                            if(set_no3==11)begin

                                input_num<=-(10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+2);

                            end

                            else begin

                                input_num <= 100000*set_no2+10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+2;

                            end

                        end

                        sw_s6: begin

                                sw_status <= sw_error;
                                set_no1 <= -1;
                                set_no2 <= 15;
                                set_no3 <= 16;
                                set_no4 <= 16;
                                set_no5 <= 17;
                                set_no6 <= 16;

                        end

                    endcase

                end

                'h0400: begin ///// 3 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=3;
                            input_num<=3;

                        end

                        sw_error: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=3;
                            input_num<=3;

                        end

                        sw_s1: begin

                            sw_status <= sw_s2;
                            set_no5<=set_no6;
                            set_no6<=3;

                            if(set_no6==11)begin

                                input_num<=-3;

                            end

                            else begin

                                input_num <= 10*set_no6+3;

                            end

                        end

                        sw_s2: begin

                            sw_status <= sw_s3;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=3;

                            if(set_no5==11)begin

                                input_num<=-(10*set_no6+3);

                            end

                            else begin

                                input_num <= 100*set_no5+10*set_no6+3;

                            end

                        end

                        sw_s3: begin

                            sw_status <= sw_s4;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=3;

                            if(set_no4==11)begin

                                input_num<=-(100*set_no5+10*set_no6+3);

                            end

                            else begin

                                input_num <= 1000*set_no4+100*set_no5+10*set_no6+3;

                            end

                        end

                        sw_s4: begin

                            sw_status <= sw_s5;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=3;

                            if(set_no3==11)begin

                                input_num<=-(1000*set_no4+100*set_no5+10*set_no6+3);

                            end

                            else begin

                                input_num <= 10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+3;

                            end

                        end

                        sw_s5: begin

                            sw_status <= sw_s6;
                            set_no1<=set_no2;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=3;

                            if(set_no3==11)begin

                                input_num<=-(10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+3);

                            end

                            else begin

                                input_num <= 100000*set_no2+10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+3;

                            end

                        end

                        sw_s6: begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end

                'h0800: begin ///// * /////

                    if (sw_status == sw_idle) begin

                        if(input_op != 2) begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                        else begin

                            sw_status <= sw_idle;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 12;
                            op_status <= 1;
                            store_op <= 6;
                            input_op <= 7;

                        end

                    end

                    else begin

                        sw_status <= sw_idle;
                        set_no1 <= -1;
                        set_no2 <= -1;
                        set_no3 <= -1;
                        set_no4 <= -1;
                        set_no5 <= -1;
                        set_no6 <= 12;
                        op_status <= 1;
                        store_op <= input_op;
                        input_op <= 2;

                    end

                end


                ///// 2nd row /////

                'h0010: begin ///// 4 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=4;

                            input_num<=4;
                        end

                        sw_error: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=4;
                            input_num<=4;

                        end

                        sw_s1: begin

                            sw_status <= sw_s2;
                            set_no5<=set_no6;
                            set_no6<=4;

                            if(set_no6==11)begin

                                input_num<=-4;

                            end

                            else begin

                                input_num <= 10*set_no6+4;

                            end

                        end

                        sw_s2: begin

                            sw_status <= sw_s3;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=4;

                            if(set_no5==11)begin

                                input_num<=-(10*set_no6+4);

                            end

                            else begin

                                input_num <= 100*set_no5+10*set_no6+4;

                            end

                        end

                        sw_s3: begin

                            sw_status <= sw_s4;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=4;

                            if(set_no4==11)begin

                                input_num<=-(100*set_no5+10*set_no6+4);

                            end

                            else begin

                                input_num <= 1000*set_no4+100*set_no5+10*set_no6+4;

                            end

                        end

                        sw_s4: begin

                            sw_status <= sw_s5;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=4;

                            if(set_no3==11)begin

                                input_num<=-(1000*set_no4+100*set_no5+10*set_no6+4);

                            end

                            else begin

                                input_num <= 10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+4;

                            end

                        end

                        sw_s5: begin

                            sw_status <= sw_s6;
                            set_no1<=set_no2;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=4;

                            if(set_no3==11)begin

                                input_num<=-(10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+4);

                            end

                            else begin

                                input_num <= 100000*set_no2+10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+4;

                            end

                        end

                        sw_s6: begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end

                'h0020: begin ///// 5 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=5;
                            input_num<=5;

                        end

                        sw_error: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=5;
                            input_num<=5;

                        end

                        sw_s1: begin

                            sw_status <= sw_s2;
                            set_no5<=set_no6;
                            set_no6<=5;

                            if(set_no6==11)begin

                                input_num<=-5;

                            end

                            else begin

                                input_num <= 10*set_no6+5;

                            end

                        end

                        sw_s2: begin

                            sw_status <= sw_s3;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=5;

                            if(set_no5==11)begin

                                input_num<=-(10*set_no6+5);

                            end

                            else begin

                                input_num <= 100*set_no5+10*set_no6+5;

                            end

                        end

                        sw_s3: begin

                            sw_status <= sw_s4;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=5;

                            if(set_no4==11)begin

                                input_num<=-(100*set_no5+10*set_no6+5);

                            end

                            else begin

                                input_num <= 1000*set_no4+100*set_no5+10*set_no6+5;

                            end

                        end

                        sw_s4: begin

                            sw_status <= sw_s5;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=5;

                            if(set_no3==11)begin

                                input_num<=-(1000*set_no4+100*set_no5+10*set_no6+5);

                            end

                            else begin

                                input_num <= 10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+5;

                            end

                        end

                        sw_s5: begin

                            sw_status <= sw_s6;
                            set_no1<=set_no2;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=5;

                            if(set_no3==11)begin

                                input_num<=-(10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+5);

                            end

                            else begin

                                input_num <= 100000*set_no2+10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+5;

                            end

                        end

                        sw_s6: begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end

                'h0040: begin ///// 6 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 6;

                            input_num <= 6;

                        end

                        sw_error : begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 6;
                            input_num <= 6;

                        end

                        sw_s1 : begin

                            sw_status <= sw_s2;
                            set_no5 <= set_no6;
                            set_no6 <= 6;

                            if (set_no6 == 11)begin

                                input_num <= -6;

                            end

                            else begin

                                input_num <= 10 * set_no6 + 6;

                            end

                        end

                        sw_s2 : begin

                            sw_status <= sw_s3;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 6;

                            if (set_no5 == 11)begin

                                input_num <= -(10 * set_no6 + 6);

                            end

                            else begin

                                input_num <= 100 * set_no5 + 10 * set_no6 + 6;

                            end

                        end

                        sw_s3 : begin

                            sw_status <= sw_s4;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 6;

                            if (set_no4 == 11)begin

                                input_num <= -(100 * set_no5 + 10 * set_no6 + 6);

                            end

                            else begin

                                input_num <= 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 6;

                            end

                        end

                        sw_s4 : begin

                            sw_status <= sw_s5;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 6;

                            if (set_no3 == 11)begin

                            input_num <= -(1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 6);

                            end

                            else begin

                            input_num <= 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 6;

                            end

                        end

                        sw_s5 : begin

                            sw_status <= sw_s6;
                            set_no1 <= set_no2;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 6;

                            if (set_no3 == 11)begin

                                input_num <= -(10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 6);

                            end

                            else begin

                                input_num <= 100000 * set_no2 + 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 6;

                            end

                        end

                        sw_s6 : begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end

                'h0080: begin ///// - /////

                    if (sw_status == sw_idle) begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                    end

                    else begin

                        sw_status <= sw_idle;
                        set_no1 <= -1;
                        set_no2 <= -1;
                        set_no3 <= -1;
                        set_no4 <= -1;
                        set_no5 <= -1;
                        set_no6 <= 11;
                        op_status <= 1;
                        store_op <= input_op;
                        input_op <= 1;

                    end

                end


                ////// 1st row /////

                'h0001: begin ///// 7 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 7;

                            input_num <= 7;

                        end

                        sw_error : begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 7;
                            input_num <= 7;

                        end

                        sw_s1 : begin

                            sw_status <= sw_s2;
                            set_no5 <= set_no6;
                            set_no6 <= 7;

                            if (set_no6 == 11)begin

                                input_num <= -7;

                            end

                            else begin

                                input_num <= 10 * set_no6 + 7;

                            end

                        end

                        sw_s2 : begin

                            sw_status <= sw_s3;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 7;

                            if (set_no5 == 11)begin

                                input_num <= -(10 * set_no6 + 7);

                            end

                            else begin

                                input_num <= 100 * set_no5 + 10 * set_no6 + 7;

                            end

                        end

                        sw_s3 : begin

                            sw_status <= sw_s4;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 7;

                            if (set_no4 == 11)begin

                            input_num <= -(100 * set_no5 + 10 * set_no6 + 7);

                            end

                            else begin
                                input_num <= 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 7;
                            end

                        end

                        sw_s4 : begin

                            sw_status <= sw_s5;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 7;

                            if (set_no3 == 11)begin

                                input_num <= -(1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 7);

                            end

                            else begin

                                input_num <= 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 7;

                            end

                        end

                        sw_s5 : begin

                            sw_status <= sw_s6;
                            set_no1 <= set_no2;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 7;

                            if (set_no3 == 11)begin

                                input_num <= -(10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 7);

                            end

                            else begin

                                input_num <= 100000 * set_no2 + 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 7;

                            end
                        end

                        sw_s6 : begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end


                'h0002: begin ///// 8 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 8;
                            input_num <= 8;

                        end

                        sw_error : begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 8;
                            input_num <= 8;

                        end

                        sw_s1 : begin

                            sw_status <= sw_s2;
                            set_no5 <= set_no6;
                            set_no6 <= 8;

                            if (set_no6 == 11)begin

                                input_num <= -8;

                            end

                            else begin

                                input_num <= 10 * set_no6 + 8;

                            end

                        end

                        sw_s2 : begin

                            sw_status <= sw_s3;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 8;

                            if (set_no5 == 11)begin

                                input_num <= -(10 * set_no6 + 8);

                            end

                            else begin

                                input_num <= 100 * set_no5 + 10 * set_no6 + 8;

                            end

                        end

                        sw_s3 : begin

                            sw_status <= sw_s4;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 8;

                            if (set_no4 == 11)begin

                                input_num <= -(100 * set_no5 + 10 * set_no6 + 8);

                            end

                            else begin

                                input_num <= 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 8;

                            end

                        end

                        sw_s4 : begin

                            sw_status <= sw_s5;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 8;

                            if (set_no3 == 11)begin

                                input_num <= -(1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 8);

                            end

                            else begin

                                input_num <= 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 8;

                            end

                        end

                        sw_s5 : begin

                            sw_status <= sw_s6;
                            set_no1 <= set_no2;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 8;

                            if (set_no3 == 11)begin

                                input_num <= -(10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 8);

                            end

                            else begin

                                input_num <= 100000 * set_no2 + 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 8;

                            end
                        end

                        sw_s6 : begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end


                'h0004: begin ///// 9 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 9;
                            input_num <= 9;

                        end

                        sw_error : begin

                            sw_status <= sw_s1;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 9;
                            input_num <= 9;

                        end

                        sw_s1 : begin

                            sw_status <= sw_s2;
                            set_no5 <= set_no6;
                            set_no6 <= 9;

                            if (set_no6 == 11)begin

                                input_num <= -9;

                            end

                            else begin

                                input_num <= 10 * set_no6 + 9;

                            end

                        end

                        sw_s2 : begin

                            sw_status <= sw_s3;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 9;

                            if (set_no5 == 11)begin

                                input_num <= -(10 * set_no6 + 9);

                            end

                            else begin

                                input_num <= 100 * set_no5 + 10 * set_no6 + 9;

                            end

                        end

                        sw_s3 : begin

                            sw_status <= sw_s4;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 9;

                            if (set_no4 == 11)begin

                                input_num <= -(100 * set_no5 + 10 * set_no6 + 9);

                            end

                            else begin

                                input_num <= 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 9;

                            end

                        end

                        sw_s4 : begin

                            sw_status <= sw_s5;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 9;

                            if (set_no3 == 11)begin

                                input_num <= -(1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 9);

                            end

                            else begin

                                input_num <= 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 9;

                            end

                        end

                        sw_s5 : begin

                            sw_status <= sw_s6;
                            set_no1 <= set_no2;
                            set_no2 <= set_no3;
                            set_no3 <= set_no4;
                            set_no4 <= set_no5;
                            set_no5 <= set_no6;
                            set_no6 <= 9;

                            if (set_no3 == 11)begin

                                input_num <= -(10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 9);

                            end

                            else begin

                                input_num <= 100000 * set_no2 + 10000 * set_no3 + 1000 * set_no4 + 100 * set_no5 + 10 * set_no6 + 9;

                            end

                        end

                        sw_s6 : begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end


                'h0008: begin ///// + /////

                    if (sw_status == sw_idle) begin

                        if(input_op != 0) begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                        else begin

                            sw_status <= sw_idle;
                            set_no1 <= -1;
                            set_no2 <= -1;
                            set_no3 <= -1;
                            set_no4 <= -1;
                            set_no5 <= -1;
                            set_no6 <= 12;
                            op_status <= 1;
                            store_op <= 7;
                            input_op <= 10;

                        end

                    end

                    else begin

                        sw_status <= sw_idle;
                        set_no1 <= -1;
                        set_no2 <= -1;
                        set_no3 <= -1;
                        set_no4 <= -1;
                        set_no5 <= -1;
                        set_no6 <= 10;
                        op_status <= 1;
                        store_op <= input_op;
                        input_op <= 0;

                    end

                end


                ///// 4th row /////

                'h1000: begin ///// % /////

                    if (sw_status == sw_idle) begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;
                            store_num<=0;
                            input_num<=0;
                            input_op<=0;
                            store_op<=0;

                    end

                    else begin

                        sw_status <= sw_idle;
                        set_no1 <= -1;
                        set_no2 <= -1;
                        set_no3 <= -1;
                        set_no4 <= -1;
                        set_no5 <= -1;
                        set_no6 <= 14;
                        op_status <= 1;
                        store_op <= input_op;
                        input_op <= 5;

                    end

                end

                'h2000: begin ///// 0 /////

                    case (sw_status)

                        sw_idle: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=0;
                            input_num<=0;

                        end

                        sw_error: begin

                            sw_status <= sw_s1;
                            set_no1<=-1;
                            set_no2<=-1;
                            set_no3<=-1;
                            set_no4<=-1;
                            set_no5<=-1;
                            set_no6<=0;
                            input_num<=0;

                        end

                        sw_s1: begin

                            sw_status <= sw_s2;
                            set_no5<=set_no6;
                            set_no6<=0;

                            if(set_no6==11)begin

                                input_num<=-0;

                            end

                            else begin

                                input_num <= 10*set_no6+0;

                            end

                        end

                        sw_s2: begin

                            sw_status <= sw_s3;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=0;

                            if(set_no5==11)begin

                                input_num<=-(10*set_no6+0);

                            end

                            else begin

                                input_num <= 100*set_no5+10*set_no6+0;

                            end

                        end

                        sw_s3: begin

                            sw_status <= sw_s4;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=0;

                            if(set_no4==11)begin

                                input_num<=-(100*set_no5+10*set_no6+0);

                            end

                            else begin

                                input_num <= 1000*set_no4+100*set_no5+10*set_no6+0;

                            end

                        end

                        sw_s4: begin

                            sw_status <= sw_s5;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=0;

                            if(set_no3==11)begin

                                input_num<=-(1000*set_no4+100*set_no5+10*set_no6+0);

                            end

                            else begin

                                input_num <= 10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+0;

                            end

                        end

                        sw_s5: begin

                            sw_status <= sw_s6;
                            set_no1<=set_no2;
                            set_no2<=set_no3;
                            set_no3<=set_no4;
                            set_no4<=set_no5;
                            set_no5<=set_no6;
                            set_no6<=0;

                            if(set_no3==11)begin

                                input_num<=-(10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+0);

                            end

                            else begin

                                input_num <= 100000*set_no2+10000*set_no3+1000*set_no4+100*set_no5+10*set_no6+0;

                            end

                        end

                        sw_s6: begin

                            sw_status <= sw_error;
                            set_no1 <= -1;
                            set_no2 <= 15;
                            set_no3 <= 16;
                            set_no4 <= 16;
                            set_no5 <= 17;
                            set_no6 <= 16;

                        end

                    endcase

                end

                'h4000: begin ///// = /////

                    if (sw_status == sw_idle) begin

                        set_no1 <= 11;
                        set_no2 <= 11;
                        set_no3 <= 11;
                        set_no4 <= 11;
                        set_no5 <= 11;
                        set_no6 <= 11;
                        store_num<=0;
                        input_num<=0;
                        input_op<=0;
                        store_op<=0;

                    end

                    else begin

                        sw_status <= sw_idle;
                        op_status <= 1;
                        store_op <= input_op;
                        input_op <= 4;

                    end

                end

                'h8000: begin ///// / /////

                    if(sw_status == sw_idle) begin

                        sw_status <= sw_error;
                        set_no1 <= -1;
                        set_no2 <= 15;
                        set_no3 <= 16;
                        set_no4 <= 16;
                        set_no5 <= 17;
                        set_no6 <= 16;
                        store_num<=0;
                        input_num<=0;
                        input_op<=0;
                        store_op<=0;

                    end

                    else begin

                        sw_status <= sw_idle;
                        set_no1 <= -1;
                        set_no2 <= -1;
                        set_no3 <= -1;
                        set_no4 <= -1;
                        set_no5 <= -1;
                        set_no6 <= 13;
                        op_status <= 1;
                        store_op <= input_op;
                        input_op <= 3;

                    end

                end

            endcase

        end

        ///// calculating /////
        if(op_status==1) begin

            ///// Addition /////
            if(store_op==0)begin

                    if(store_num + input_num>999999)begin

                        error <= 1;

                    end

                    else begin

                        store_num <= store_num + input_num;

                    end

            end

            ///// Subtraction /////
            else if(store_op==1)begin

                    if(store_num - input_num<-99999)begin

                        error <= 1;

                    end

                    else begin

                        store_num <= store_num - input_num;

                    end

            end

            ///// Multiplication /////
            else if(store_op==2)begin

                    if((store_num * input_num>999999) || (store_num * input_num<-99999))begin

                        error <= 1;

                    end

                    else begin

                        store_num <= store_num * input_num;

                    end

            end

            ///// Division /////
            else if(store_op==3)begin

                if(input_num !=0)begin

                    store_num <= store_num / input_num;

                end

                else if (input_num==0)begin

                    error <= 1;

                end

            end

            ///// Remainder /////
            else if(store_op==5)begin

                if(input_num !=0)begin

                    store_num <= store_num - input_num*(store_num / input_num);

                end

                else if (input_num==0)begin

                    store_num <= store_num;

                end

                else begin

                   error <= 1;

                end

            end

            ///// Exponentiation /////
            else if(store_op==6)begin

                if(store_num * store_num>999999)begin

                    error<=1;

                end

                else begin

                    store_num <= store_num * store_num;

                end

            end

            ///// Harshad number /////
            else if(store_op==7)begin

                if(store_num<=0)begin

                    error<=1;

                end

                else begin

                    store_num <= store_num%((store_num/100000)+((store_num%10000)/1000)+((store_num%1000)/100)+((store_num%100)/10)+((store_num%10)));
                    hsd <= 1;

                end

            end

            else begin

                store_num <= input_num;

            end


            if (input_op==4)begin

                op_status <= 2;

            end

            else begin

                op_status <= 0;
                input_num <= 0;
                display <= 1;

            end

        end

        ///// pushing '=' button /////
        else if(op_status==2) begin

            op_status <= 0;
            input_num <= 0;
            display <= 1;

        end

        ///// setting number(set_no) for 7-segment display /////
        else if(display==1) begin

            display <= 0;
            set_no1 <= -1;
            set_no2 <= -1;
            set_no3 <= -1;
            set_no4 <= -1;
            set_no5 <= -1;
            set_no6 <= -1;


            if (error == 1) begin

                error <= 0;
                set_no1 <= -1;
                set_no2 <= 15;
                set_no3 <= 16;
                set_no4 <= 16;
                set_no5 <= 17;
                set_no6 <= 16;

            end


            else if(hsd == 1) begin

                if (store_num == 0) begin

                    hsd <= 0;
                    ///// TRUE /////
                    set_no1 <= -1;
                    set_no2 <= -1;
                    set_no3 <= 18;
                    set_no4 <= 19;
                    set_no5 <= 20;
                    set_no6 <= 15;

                end

                else begin

                    hsd <= 0;
                    ///// FALSE /////
                    set_no1 <= -1;
                    set_no2 <= 21;
                    set_no3 <= 22;
                    set_no4 <= 23;
                    set_no5 <= 24;
                    set_no6 <= 15;

                end

            end


            else if(store_num > -100000 && store_num <= -10000 )begin

                set_no1 <= 11;
                set_no2 <= ((-store_num)%100000)/10000;
                set_no3 <= ((-store_num)%10000)/1000;
                set_no4 <= ((-store_num)%1000)/100;
                set_no5 <= ((-store_num)%100)/10;
                set_no6 <= ((-store_num)%10);

            end


            else if(store_num > -10000 && store_num <= -1000 )begin

                set_no1 <= -1;
                set_no2 <= 11;
                set_no3 <= ((-store_num)%10000)/1000;
                set_no4 <= ((-store_num)%1000)/100;
                set_no5 <= ((-store_num)%100)/10;
                set_no6 <= ((-store_num)%10);

            end


            else if(store_num > -1000 && store_num <= -100 )begin

                set_no1 <= -1;
                set_no2 <= -1;
                set_no3 <= 11;
                set_no4 <= ((-store_num)%1000)/100;
                set_no5 <= ((-store_num)%100)/10;
                set_no6 <= ((-store_num)%10);

            end


            else if(store_num > -100 && store_num <= -10 )begin

                set_no1 <= -1;
                set_no2 <= -1;
                set_no3 <= -1;
                set_no4 <= 11;
                set_no5 <= ((-store_num)%100)/10;
                set_no6 <= ((-store_num)%10);

            end


            else if(store_num > -10 && store_num < 0 )begin

                set_no1 <= -1;
                set_no2 <= -1;
                set_no3 <= -1;
                set_no4 <= -1;
                set_no5 <= 11;
                set_no6 <= ((-store_num)%10);

            end


            else if(store_num >= 0 && store_num < 10 )begin

                set_no1 <= -1;
                set_no2 <= -1;
                set_no3 <= -1;
                set_no4 <= -1;
                set_no5 <= -1;
                set_no6 <= store_num;

            end


            else if(store_num >= 10 && store_num < 100 )begin

                set_no1 <= -1;
                set_no2 <= -1;
                set_no3 <= -1;
                set_no4 <= -1;
                set_no5 <= ((store_num)%100)/10;
                set_no6 <= ((store_num)%10);

            end


            else if(store_num >= 100 && store_num < 1000 )begin

                set_no1 <= -1;
                set_no2 <= -1;
                set_no3 <= -1;
                set_no4 <= ((store_num)%1000)/100;
                set_no5 <= ((store_num)%100)/10;
                set_no6 <= ((store_num)%10);

            end


            else if(store_num >= 1000 && store_num < 10000 )begin

                set_no1 <= -1;
                set_no2 <= -1;
                set_no3 <= ((store_num)%10000)/1000;
                set_no4 <= ((store_num)%1000)/100;
                set_no5 <= ((store_num)%100)/10;
                set_no6 <= ((store_num)%10);

            end


            else if(store_num >= 10000 && store_num < 100000 )begin

                set_no1 <= -1;
                set_no2 <= ((store_num)%100000)/10000;
                set_no3 <= ((store_num)%10000)/1000;
                set_no4 <= ((store_num)%1000)/100;
                set_no5 <= ((store_num)%100)/10;
                set_no6 <= ((store_num)%10);

            end


            else if(store_num >= 100000)begin

                set_no1 <= ((store_num)%1000000)/100000;
                set_no2 <= ((store_num)%100000)/10000;
                set_no3 <= ((store_num)%10000)/1000;
                set_no4 <= ((store_num)%1000)/100;
                set_no5 <= ((store_num)%100)/10;
                set_no6 <= ((store_num)%10);

            end


            else begin
                ///// Error /////
                set_no1 <= -1;
                set_no2 <= 15;
                set_no3 <= 16;
                set_no4 <= 16;
                set_no5 <= 17;
                set_no6 <= 16;

            end

        end

    end


    ///// 8-bits settings for 7-segment display /////
    always @(set_no1) begin

        case (set_no1)

            0: seg_100000 <= 8'b0011_1111;         //0
            1: seg_100000 <= 8'b0000_0110;         //1
            2: seg_100000 <= 8'b0101_1011;         //2
            3: seg_100000 <= 8'b0100_1111;         //3
            4: seg_100000 <= 8'b0110_0110;         //4
            5: seg_100000 <= 8'b0110_1101;         //5
            6: seg_100000 <= 8'b0111_1101;         //6
            7: seg_100000 <= 8'b0000_0111;         //7
            8: seg_100000 <= 8'b0111_1111;         //8
            9: seg_100000 <= 8'b0110_0111;         //9
            10: seg_100000 <= 8'b0100_0110;         // +
            11: seg_100000 <= 8'b0100_0000;         // -
            12: seg_100000 <= 8'b0110_0100;         // *
            13: seg_100000 <= 8'b0101_0010;         // /
            14: seg_100000 <= 8'b0001_1100;         // %
            15: seg_100000 <= 8'b0111_1001;         // BIG E
            16: seg_100000 <= 8'b0101_0000;         // small r
            17: seg_100000 <= 8'b0101_1100;         // small o
            18: seg_100000 <= 8'b0111_1000;         // t
            19: seg_100000 <= 8'b0011_0011;         // BIG R
            20: seg_100000 <= 8'b0011_1110;         // BIG U
            21: seg_100000 <= 8'b0111_0001;         // BIG F
            22: seg_100000 <= 8'b0111_0111;         // BIG A
            23: seg_100000 <= 8'b0011_1000;         // BIG L
            24: seg_100000 <= 8'b0110_1101;         // BIG S
            default: seg_100000 <= 8'b0000_0000;

        endcase

    end

    always @(set_no2) begin

        case (set_no2)

            0: seg_10000 <= 8'b0011_1111;         //0
            1: seg_10000 <= 8'b0000_0110;         //1
            2: seg_10000 <= 8'b0101_1011;         //2
            3: seg_10000 <= 8'b0100_1111;         //3
            4: seg_10000 <= 8'b0110_0110;         //4
            5: seg_10000 <= 8'b0110_1101;         //5
            6: seg_10000 <= 8'b0111_1101;         //6
            7: seg_10000 <= 8'b0000_0111;         //7
            8: seg_10000 <= 8'b0111_1111;         //8
            9: seg_10000 <= 8'b0110_0111;         //9
            10: seg_10000 <= 8'b0100_0110;         // +
            11: seg_10000 <= 8'b0100_0000;         // -
            12: seg_10000 <= 8'b0110_0100;         // *
            13: seg_10000 <= 8'b0101_0010;         // /
            14: seg_10000 <= 8'b0001_1100;         // %
            15: seg_10000 <= 8'b0111_1001;         // BIG E
            16: seg_10000 <= 8'b0101_0000;         // small r
            17: seg_10000 <= 8'b0101_1100;         // small o
            18: seg_10000 <= 8'b0111_1000;         // t
            19: seg_10000 <= 8'b0011_0011;         // BIG R
            20: seg_10000 <= 8'b0011_1110;         // BIG U
            21: seg_10000 <= 8'b0111_0001;         // BIG F
            22: seg_10000 <= 8'b0111_0111;         // BIG A
            23: seg_10000 <= 8'b0011_1000;         // BIG L
            24: seg_10000 <= 8'b0110_1101;         // BIG S
            default: seg_10000 <= 8'b0000_0000;

        endcase

    end

    always @(set_no3) begin

        case (set_no3)

            0: seg_1000 <= 8'b0011_1111;         //0
            1: seg_1000 <= 8'b0000_0110;         //1
            2: seg_1000 <= 8'b0101_1011;         //2
            3: seg_1000 <= 8'b0100_1111;         //3
            4: seg_1000 <= 8'b0110_0110;         //4
            5: seg_1000 <= 8'b0110_1101;         //5
            6: seg_1000 <= 8'b0111_1101;         //6
            7: seg_1000 <= 8'b0000_0111;         //7
            8: seg_1000 <= 8'b0111_1111;         //8
            9: seg_1000 <= 8'b0110_0111;         //9
            10: seg_1000 <= 8'b0100_0110;         // +
            11: seg_1000 <= 8'b0100_0000;         // -
            12: seg_1000 <= 8'b0110_0100;         // *
            13: seg_1000 <= 8'b0101_0010;         // /
            14: seg_1000 <= 8'b0001_1100;         // %
            15: seg_1000 <= 8'b0111_1001;         // BIG E
            16: seg_1000 <= 8'b0101_0000;         // small r
            17: seg_1000 <= 8'b0101_1100;         // small o
            18: seg_1000 <= 8'b0111_1000;         // t
            19: seg_1000 <= 8'b0011_0011;         // BIG R
            20: seg_1000 <= 8'b0011_1110;         // BIG U
            21: seg_1000 <= 8'b0111_0001;         // BIG F
            22: seg_1000 <= 8'b0111_0111;         // BIG A
            23: seg_1000 <= 8'b0011_1000;         // BIG L
            24: seg_1000 <= 8'b0110_1101;         // BIG S
            default: seg_1000 <= 8'b0000_0000;

        endcase

    end

    always @(set_no4) begin

        case (set_no4)

            0: seg_100 <= 8'b0011_1111;         //0
            1: seg_100 <= 8'b0000_0110;         //1
            2: seg_100 <= 8'b0101_1011;         //2
            3: seg_100 <= 8'b0100_1111;         //3
            4: seg_100 <= 8'b0110_0110;         //4
            5: seg_100 <= 8'b0110_1101;         //5
            6: seg_100 <= 8'b0111_1101;         //6
            7: seg_100 <= 8'b0000_0111;         //7
            8: seg_100 <= 8'b0111_1111;         //8
            9: seg_100 <= 8'b0110_0111;         //9
            10: seg_100 <= 8'b0100_0110;         // +
            11: seg_100 <= 8'b0100_0000;         // -
            12: seg_100 <= 8'b0110_0100;         // *
            13: seg_100 <= 8'b0101_0010;         // /
            14: seg_100 <= 8'b0001_1100;         // %
            15: seg_100 <= 8'b0111_1001;         // BIG E
            16: seg_100 <= 8'b0101_0000;         // small r
            17: seg_100 <= 8'b0101_1100;         // small o
            18: seg_100 <= 8'b0111_1000;         // t
            19: seg_100 <= 8'b0011_0011;         // BIG R
            20: seg_100 <= 8'b0011_1110;         // BIG U
            21: seg_100 <= 8'b0111_0001;         // BIG F
            22: seg_100 <= 8'b0111_0111;         // BIG A
            23: seg_100 <= 8'b0011_1000;         // BIG L
            24: seg_100 <= 8'b0110_1101;         // BIG S
            default: seg_100 <= 8'b0000_0000;

        endcase

    end

    always @(set_no5) begin

        case (set_no5)

            0: seg_10 <= 8'b0011_1111;         //0
            1: seg_10 <= 8'b0000_0110;         //1
            2: seg_10 <= 8'b0101_1011;         //2
            3: seg_10 <= 8'b0100_1111;         //3
            4: seg_10 <= 8'b0110_0110;         //4
            5: seg_10 <= 8'b0110_1101;         //5
            6: seg_10 <= 8'b0111_1101;         //6
            7: seg_10 <= 8'b0000_0111;         //7
            8: seg_10 <= 8'b0111_1111;         //8
            9: seg_10 <= 8'b0110_0111;         //9
            10: seg_10 <= 8'b0100_0110;         // +
            11: seg_10 <= 8'b0100_0000;         // -
            12: seg_10 <= 8'b0110_0100;         // *
            13: seg_10 <= 8'b0101_0010;         // /
            14: seg_10 <= 8'b0001_1100;         // %
            15: seg_10 <= 8'b0111_1001;         // BIG E
            16: seg_10 <= 8'b0101_0000;         // small r
            17: seg_10 <= 8'b0101_1100;         // small o
            18: seg_10 <= 8'b0111_1000;         // t
            19: seg_10 <= 8'b0011_0011;         // BIG R
            20: seg_10 <= 8'b0011_1110;         // BIG U
            21: seg_10 <= 8'b0111_0001;         // BIG F
            22: seg_10 <= 8'b0111_0111;         // BIG A
            23: seg_10 <= 8'b0011_1000;         // BIG L
            24: seg_10 <= 8'b0110_1101;         // BIG S
            default: seg_10 <= 8'b0000_0000;

        endcase

    end

    always @(set_no6) begin

        case (set_no6)

            0: seg_1 <= 8'b0011_1111;         //0
            1: seg_1 <= 8'b0000_0110;         //1
            2: seg_1 <= 8'b0101_1011;         //2
            3: seg_1 <= 8'b0100_1111;         //3
            4: seg_1 <= 8'b0110_0110;         //4
            5: seg_1 <= 8'b0110_1101;         //5
            6: seg_1 <= 8'b0111_1101;         //6
            7: seg_1 <= 8'b0000_0111;         //7
            8: seg_1 <= 8'b0111_1111;         //8
            9: seg_1 <= 8'b0110_0111;         //9
            10: seg_1 <= 8'b0100_0110;         // +
            11: seg_1 <= 8'b0100_0000;         // -
            12: seg_1 <= 8'b0110_0100;         // *
            13: seg_1 <= 8'b0101_0010;         // /
            14: seg_1 <= 8'b0001_1100;         // %
            15: seg_1 <= 8'b0111_1001;         // BIG E
            16: seg_1 <= 8'b0101_0000;         // small r
            17: seg_1 <= 8'b0101_1100;         // small o
            18: seg_1 <= 8'b0111_1000;         // t
            19: seg_1 <= 8'b0011_0011;         // BIG R
            20: seg_1 <= 8'b0011_1110;         // BIG U
            21: seg_1 <= 8'b0111_0001;         // BIG F
            22: seg_1 <= 8'b0111_0111;         // BIG A
            23: seg_1 <= 8'b0011_1000;         // BIG L
            24: seg_1 <= 8'b0110_1101;         // BIG S
            default: seg_1 <= 8'b0000_0000;

        endcase

    end


    ///// fnd_clk; fnd_d <= 8-bits for 7-segment ; fnd_s <= six 7-segment display address /////
    always @(posedge fnd_clk) begin

        fnd_cnt <= fnd_cnt + 1;

        case (fnd_cnt)

            5: begin

                fnd_d <= seg_100000;
                fnd_s <= 8'b0101_1111;

            end

            4: begin

                fnd_d <= seg_10000;
                fnd_s <= 8'b0110_1111;

            end

            3: begin

                fnd_d <= seg_1000;
                fnd_s <= 8'b0111_0111;

            end

            2: begin

                fnd_d <= seg_100;
                fnd_s <= 8'b0111_1011;

            end

            1: begin

                fnd_d <= seg_10;
                fnd_s <= 8'b0111_1101;

            end

            0: begin

                fnd_d <= seg_1;
                fnd_s <= 8'b0111_1110;

            end

        endcase

    end


endmodule
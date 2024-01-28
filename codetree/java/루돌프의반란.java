package codetree.java;

import java.util.*;
import java.io.*;

public class 루돌프의반란 {

    static class Rudolf {
        int x, y;
        int mx, my;

        Rudolf(int x, int y) {
            this.x = x;
            this.y = y;
            this.mx = 0;
            this.my = 0;
        }

        void move() {
            int dist = Integer.MAX_VALUE;
            int x = -1;
            int y = -1;
            for (int i = 0; i < P; i++) {
                if (summary[i].death) continue;
                int tmpDist = summary[i].getDistance(this);
                if (tmpDist < dist) {
                    dist = tmpDist;
                    x = summary[i].x;
                    y = summary[i].y;
                } else if (tmpDist == dist) {
                    if (summary[i].x > x) {
                        x = summary[i].x;
                        y = summary[i].y;
                    } else if (summary[i].x == x) {
                        if (summary[i].y > y) {
                            x = summary[i].x;
                            y = summary[i].y;
                        }
                    }
                }
            }

            int moveDist = Integer.MAX_VALUE;
            int moveX = -1;
            int moveY = -1;
            int moveIdx = -1;
            for (int idx = 0; idx < dxs.length; idx++) {
                int nx = this.x + dxs[idx];
                int ny = this.y + dys[idx];
                if (!isPossible(nx, ny)) continue;
                if (distance(x, nx, y, ny) >= moveDist) continue;
                moveDist = distance(x, nx, y, ny);
                moveX = nx;
                moveY = ny;
                moveIdx = idx;
            }

            this.x = moveX;
            this.y = moveY;
            this.mx = dxs[moveIdx];
            this.my = dys[moveIdx];
        }

    }

    static class Santa{
        int x, y, num;
        int mx, my;
        int score, stunned;
        boolean death;
        Santa(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.mx = 0;
            this.my = 0;
            this.num = num;
            this.death = false;
            this.stunned = 0;
            this.score = 0;
        } 

        int getDistance(Rudolf s) {
            return distance(this.x, s.x, this.y, s.y);  
        }

        void setStunned(int x) {
            this.stunned = x;
        }

        void move() {
            int dist = getDistance(rudolf);
            int x = this.x;
            int y = this.y;
            int IDX = -1;
            for (int idx = 0; idx < dx.length; idx++) {
                int nx = this.x + dx[idx];
                int ny = this.y + dy[idx];
                if (!isPossible(nx, ny)) continue;
                int tmpDist = distance(nx, rudolf.x, ny, rudolf.y); 
                if (tmpDist > dist) continue;
                if (arr[nx][ny] != null) continue;
                if (dist > tmpDist) {
                    dist = tmpDist;
                    x = nx;
                    y = ny;
                    IDX = idx;
                }
            }
            if (this.x == x && this.y == y) return;

            arr[this.x][this.y] = null;
            arr[x][y] = this;
            this.x = x;
            this.y = y;
            this.mx = dx[IDX];
            this.my = dy[IDX];
        }

    }
    
    static int N, M, P, C, D;
    static int Rr, Rc;
    static Rudolf rudolf;
    static int[] dxs = {-1,-1,-1,0,0,1,1,1};
    static int[] dys = {-1,0,1,-1,1,-1,0,1};
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Santa[][] arr;
    static Santa[] summary;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        arr = new Santa[N][N];
        summary = new Santa[P];

        st = new StringTokenizer(br.readLine());
        Rr = Integer.parseInt(st.nextToken());
        Rc = Integer.parseInt(st.nextToken());
        rudolf = new Rudolf(Rr-1, Rc-1);

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Santa santa = new Santa(x-1, y-1, num-1);
            arr[x-1][y-1] = santa;
            summary[num-1] = santa;
        }

        for (int i = 0; i < M; i++) {

            if (!game()) break;
        }

        for (int i = 0; i < P; i++) {
            System.out.print(summary[i].score+" ");
        }
    }

    private static boolean game() {
        rudolfMove();
        santaMove();

        int cnt = 0;
        for (int i = 0; i < P; i++) {
            Santa s = summary[i];
            if (s.death == true) continue;
            s.score++;
            cnt++; 
        }

        return cnt == 0 ? false : true;
    }

    private static void rudolfMove() {
        rudolf.move();

        int rx = rudolf.x;
        int ry = rudolf.y;
        int rmx = rudolf.mx;
        int rmy = rudolf.my;

        if (arr[rx][ry] != null) {
            Santa s = arr[rx][ry];
            arr[rx][ry] = null;

            s.setStunned(2);

            s.score += C;
            s.x += C * rmx;
            s.y += C * rmy;
            if (!isPossible(s.x, s.y)) {
                s.death = true;
            } else {
                interact(s, rmx, rmy);
            }
        }
    }

    private static void santaMove() {
        for (int i = 0; i < P; i++) {
            Santa s = summary[i];
            if (s.death) continue;
            if (s.stunned >= 1) {
                s.stunned--;
                continue;
            }
            int preX = s.x;
            int preY = s.y;
           
            s.move();
            if (s.x == preX && s.y == preY) continue;
            
            int ox = s.x;
            int oy = s.y;

            if (s.x == rudolf.x && s.y == rudolf.y) {
                s.score += D;
                s.setStunned(1);
                s.x -= D * s.mx;
                s.y -= D * s.my;
                if (!isPossible(s.x, s.y)) {
                    s.death = true;
                } else {
                    interact(s, -s.mx, -s.my);
                }
                arr[ox][oy] = null;
            }
        }
    }


    private static void interact(Santa s, int mx, int my) {
        if (arr[s.x][s.y] == null) {
            arr[s.x][s.y] = s;
            return;
        }

        ArrayList<Santa> tmp = new ArrayList<>();
        int x = s.x;
        int y = s.y;
        while (isPossible(x, y) && arr[x][y] != null) {
            tmp.add(arr[x][y]);
            x += mx;
            y += my;
        }
        arr[s.x][s.y] = s;

        for (int i = 1; i <= tmp.size(); i++) {
            int nx = s.x + mx * i;
            int ny = s.y + my * i;
            Santa ms = tmp.get(i-1);
            if (!isPossible(nx, ny)) {
                ms.death = true;
                break;
            }
            arr[nx][ny] = ms;
            ms.x = nx;
            ms.y = ny;
        }
    }

    private static boolean isPossible(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static int distance(int x1, int x2, int y1, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);  
    }
}
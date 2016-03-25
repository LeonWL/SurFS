package com.autumn.util.zlib;

/**
 *
 * Title: deflate压缩-哈夫曼树
 *
 * Copyright: Autumn Copyright (c) 2011
 *
 * Company: Autumn
 *
 * @author 刘社朋
 * @version 2.0
 */
final class Tree {

    static final int[] extra_lbits = {
        0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0
    };

    static final int[] extra_dbits = {
        0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13
    };

    static final int[] extra_blbits = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 7
    };

    static final byte[] bl_order = {
        16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    static final byte[] _dist_code = {
        0, 1, 2, 3, 4, 4, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8,
        8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10,
        10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
        11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
        12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13,
        13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
        13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,
        14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,
        14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,
        14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15,
        15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
        15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
        15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 16, 17,
        18, 18, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22,
        23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
        24, 24, 24, 24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26,
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 27,
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27,
        27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
        28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
        28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
        28, 28, 28, 28, 28, 28, 28, 28, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29,
        29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29,
        29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29,
        29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29
    };

    static final byte[] _length_code = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 12, 12,
        13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16,
        17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19,
        19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
        21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22,
        22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23,
        23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
        24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
        25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26,
        26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26,
        26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27,
        27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28
    };

    static final int[] base_length = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14, 16, 20, 24, 28, 32, 40, 48, 56,
        64, 80, 96, 112, 128, 160, 192, 224, 0
    };

    static final int[] base_dist = {
        0, 1, 2, 3, 4, 6, 8, 12, 16, 24,
        32, 48, 64, 96, 128, 192, 256, 384, 512, 768,
        1024, 1536, 2048, 3072, 4096, 6144, 8192, 12288, 16384, 24576
    };

    short[] dyn_tree;      // the dynamic tree
    int max_code;      // largest code with non zero frequency
    StaticTree stat_desc;  // the corresponding static tree

    private void gen_bitlen(Deflate s) {
        short[] tree = dyn_tree;
        short[] stree = stat_desc.static_tree;
        int[] extra = stat_desc.extra_bits;
        int base = stat_desc.extra_base;
        int max_length = stat_desc.max_length;
        int h;              // heap index
        int n, m;           // iterate over the tree elements
        int bits;           // bit length
        int xbits;          // extra bits
        short f;            // frequency
        int overflow = 0;   // number of elements with bit length too large
        for (bits = 0; bits <= Constant.MAX_WBITS; bits++) {
            s.bl_count[bits] = 0;
        }
        tree[s.heap[s.heap_max] * 2 + 1] = 0; // root of the heap
        for (h = s.heap_max + 1; h < Constant.HEAP_SIZE; h++) {
            n = s.heap[h];
            bits = tree[tree[n * 2 + 1] * 2 + 1] + 1;
            if (bits > max_length) {
                bits = max_length;
                overflow++;
            }
            tree[n * 2 + 1] = (short) bits;
            if (n > max_code) {
                continue;  // not a leaf node
            }
            s.bl_count[bits]++;
            xbits = 0;
            if (n >= base) {
                xbits = extra[n - base];
            }
            f = tree[n * 2];
            s.opt_len += f * (bits + xbits);
            if (stree != null) {
                s.static_len += f * (stree[n * 2 + 1] + xbits);
            }
        }
        if (overflow == 0) {
            return;
        }
        do {
            bits = max_length - 1;
            while (s.bl_count[bits] == 0) {
                bits--;
            }
            s.bl_count[bits]--;      // move one leaf down the tree
            s.bl_count[bits + 1] += 2;   // move one overflow item as its brother
            s.bl_count[max_length]--;
            overflow -= 2;
        } while (overflow > 0);
        for (bits = max_length; bits != 0; bits--) {
            n = s.bl_count[bits];
            while (n != 0) {
                m = s.heap[--h];
                if (m > max_code) {
                    continue;
                }
                if (tree[m * 2 + 1] != bits) {
                    s.opt_len += ((long) bits - (long) tree[m * 2 + 1]) * (long) tree[m * 2];
                    tree[m * 2 + 1] = (short) bits;
                }
                n--;
            }
        }
    }

    void build_tree(Deflate s) {
        short[] tree = dyn_tree;
        short[] stree = stat_desc.static_tree;
        int elems = stat_desc.elems;
        int n, m;          // iterate over heap elements
        int m_code = -1;   // largest code with non zero frequency
        int node;          // new node being created
        s.heap_len = 0;
        s.heap_max = Constant.HEAP_SIZE;
        for (n = 0; n < elems; n++) {
            if (tree[n * 2] != 0) {
                s.heap[++s.heap_len] = m_code = n;
                s.depth[n] = 0;
            } else {
                tree[n * 2 + 1] = 0;
            }
        }
        while (s.heap_len < 2) {
            node = s.heap[++s.heap_len] = (m_code < 2 ? ++m_code : 0);
            tree[node * 2] = 1;
            s.depth[node] = 0;
            s.opt_len--;
            if (stree != null) {
                s.static_len -= stree[node * 2 + 1];
            }
        }
        this.max_code = m_code;
        for (n = s.heap_len / 2; n >= 1; n--) {
            pqdownheap(s, tree, n);
        }
        node = elems;                 // next internal node of the tree
        do {
            n = s.heap[1];
            s.heap[1] = s.heap[s.heap_len--];
            pqdownheap(s, tree, 1);
            m = s.heap[1];                // m = node of next least frequency
            s.heap[--s.heap_max] = n; // keep the nodes sorted by frequency
            s.heap[--s.heap_max] = m;
            tree[node * 2] = (short) (tree[n * 2] + tree[m * 2]);
            s.depth[node] = (byte) (Math.max(s.depth[n], s.depth[m]) + 1);
            tree[n * 2 + 1] = tree[m * 2 + 1] = (short) node;
            s.heap[1] = node++;
            pqdownheap(s, tree, 1);
        } while (s.heap_len >= 2);
        s.heap[--s.heap_max] = s.heap[1];
        gen_bitlen(s);
        gen_codes(tree, m_code, s.bl_count, s.next_code);
    }

    private void pqdownheap(Deflate def, short[] tree, int k) {
        int v = def.heap[k];
        int j = k << 1;  // left son of k
        while (j <= def.heap_len) {
            if (j < def.heap_len
                    && Tree.smaller(tree, def.heap[j + 1], def.heap[j], def.depth)) {
                j++;
            }
            if (Tree.smaller(tree, v, def.heap[j], def.depth)) {
                break;
            }
            def.heap[k] = def.heap[j];
            k = j;
            j <<= 1;
        }
        def.heap[k] = v;
    }

    private static void gen_codes(short[] tree, int max_code, short[] bl_count, short[] next_code) {
        short code = 0;            // running code value
        int bits;                  // bit index
        int n;                     // code index
        next_code[0] = 0;
        for (bits = 1; bits <= Constant.MAX_WBITS; bits++) {
            next_code[bits] = code = (short) ((code + bl_count[bits - 1]) << 1);
        }
        for (n = 0; n <= max_code; n++) {
            int len = tree[n * 2 + 1];
            if (len == 0) {
                continue;
            }
            tree[n * 2] = (short) (bi_reverse(next_code[len]++, len));
        }
    }

    private static int bi_reverse(int code, int len) {
        int res = 0;
        do {
            res |= code & 1;
            code >>>= 1;
            res <<= 1;
        } while (--len > 0);
        return res >>> 1;
    }

    static boolean smaller(short[] tree, int n, int m, byte[] depth) {
        short tn2 = tree[n * 2];
        short tm2 = tree[m * 2];
        return (tn2 < tm2
                || (tn2 == tm2 && depth[n] <= depth[m]));
    }

    static int d_code(int dist) {
        return ((dist) < 256 ? _dist_code[dist] : _dist_code[256 + ((dist) >>> 7)]);
    }
}

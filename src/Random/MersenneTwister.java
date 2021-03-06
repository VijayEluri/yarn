/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Random;

import Validation.*;

/**
 * Implementation of the <a href="http://en.wikipedia.org/wiki/Mersenne_twister">
 * Mersenne twister</a> pseudo {@see Generator random number generator}.<br>
 * <a href="http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/emt.html">
 * Mersenne twister homepage</a>.<br>
 * --<br>
 * Copyright (c) 2006,2007 Mutsuo Saito, Makoto Matsumoto and Hiroshima<br>
 * University. All rights reserved.<br>
 * <br>
 * Redistribution and use in source and binary forms, with or without<br>
 * modification, are permitted provided that the following conditions are<br>
 * met:<br>
 * <br>
 *     * Redistributions of source code must retain the above copyright<br>
 *       notice, this list of conditions and the following disclaimer.<br>
 *     * Redistributions in binary form must reproduce the above<br>
 *       copyright notice, this list of conditions and the following<br>
 *       disclaimer in the documentation and/or other materials provided<br>
 *       with the distribution.<br>
 *     * Neither the name of the Hiroshima University nor the names of<br>
 *       its contributors may be used to endorse or promote products<br>
 *       derived from this software without specific prior written<br>
 *       permission.<br>
 * <br>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS<br>
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT<br>
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR<br>
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT<br>
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,<br>
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT<br>
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,<br>
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY<br>
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT<br>
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE<br>
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.<br>
 * --<br>
 * @author Rune Dahl Iversen
 */
public final class MersenneTwister
        implements Seed<Long, Long>, Normalization<Long, Double> {
    private final static Validator<Long> __validator =
            Factory.BoundedLong(0, 4294967296L);
    private final static int __n = 624;
    private final static int __m = 397;
    private final static long __upperMask = 0x80000000;
    private final static long __lowerMask = 0x7fffffff;
    private final static long[] __a = new long[] { 0x0 , 0x9908b0df };

    private final static int __seedFactor = 1812433253;
    private final static double __normalize = 4294967296L;

    /* Tempering parameters */
    private final static long __maskB = 0x9d2c5680;
    private final static long __maskC = 0xefc60000;

    /* State */
    private long[] _cache = new long[__n];
    private int _index = 0;

    /**
     * Create an instance of the Mersenne twister pseudo random number generator
     * with the specified seed.<br>
     * The seed is limited to the values 0 through 4294967296.
     * @param seed Seed.
     */
    public MersenneTwister(long seed) {
        this.setSeed(seed);
    }

    @Override
    public Double getFactor() {
        return __normalize;
    }

    @Override
    public Long getSample() {
        long y;
        if (__n <= this._index) {
            for (this._index = 0; this._index < __n - __m; this._index++) {
                y = (this._cache[this._index] & __upperMask) | (_cache[this._index + 1] & __lowerMask);
                this._cache[this._index] = this._cache[this._index + __m] ^ (y >>> 1) ^ __a[(int)(y & 0x1)];
            }
            for (; this._index < __n - 1; this._index++) {
                y = (this._cache[this._index] & __upperMask) | (this._cache[this._index + 1] & __lowerMask);
                this._cache[this._index] = this._cache[this._index + __m - __n] ^ (y >>> 1) ^ __a[(int)(y & 0x1)];
            }
            y = (this._cache[this._index] & __upperMask) | (this._cache[0] & __lowerMask);
            this._cache[this._index] = this._cache[this._index + __m - __n] ^ (y >>> 1) ^ __a[(int)(y & 0x1)];
            this._index = 0;
        }
        y = this._cache[this._index++];
        y ^= _ShiftU(y);
        y ^= _ShiftS(y);
        y ^= _ShiftT(y);
        y ^= _ShiftL(y);
        return y & 4294967295L;
    }

    @Override
    public Long[] getSample(final int count) {
        Long[] simulations = new Long[count];
        for (int i = 0; i < count; i++) {
            simulations[i] = this.getSample();
        }
        return simulations;
    }

    /**
     * Set the seed of this Mersenne twister.<br>
     * The seed is limited to the values 0 through 4294967296.
     * @param seed Seed.
     */
    @Override
    public void setSeed(final Long seed) {
        if (!__validator.isValid(seed))
            throw new IllegalArgumentException(__validator.message(seed, "Seed"));
        long s = seed;
        this._cache[0] = (int)s;
        for (this._index = 1; this._index < __n; this._index++) {
            this._cache[this._index] = _SeedShift(this._cache[this._index - 1]) + this._index;
        }
        this._index = 0;
    }

    private final long _ShiftU(long value) { return value >>> 11; }
    private final long _ShiftS(long value) { return (value << 7) & __maskB; }
    private final long _ShiftT(long value) { return (value << 15) & __maskC; }
    private final long _ShiftL(long value) { return value >>> 18; }
    private final long _SeedShift(long value) {
        return __seedFactor * (value ^ (value >>> 30));
    }
}

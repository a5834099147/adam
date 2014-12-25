/*
 * Copyright (C) 2014 a5834099147(lxd) <a5834099147@126.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.lxd.algorithm;


/**
 * 弱校验和计算算法类
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2014年12月25日
 * @blog : http://a5834099147.github.io/
 * @review 
 */
public class Adler32 {
	/*
	 * the largest prime number smaller than 216
	 */
	protected static final int MOD_ADLER = 65521;

	/**
	 * @param datas  待计算的数据块
	 * @param offset   待计算的偏移
	 * @param length 待计算的长度
	 * @return  校验和
	 */
	public static int adler32( byte[] datas , int offset , int length ){
		int A = 1;
		int B = 0;
		for( int i = offset ; i< offset + length ; i++ ){
			//1. 计算A 
			A += datas[i];
			if( A >= MOD_ADLER ){
				A -= MOD_ADLER;
			}
			//2. 计算B
			B += A;
			if( B >= MOD_ADLER){
				B -= MOD_ADLER;
			}
		}
	    //3. 返回校验和
		return B << 16 | A;
	}
	
	/**
	 *  Adler-32 核心算法在于计算下一块的checksum复杂度为 O( 1 )
	 * @param oldAdler32 初始chunksum
	 * @param chunkSize  块大小
	 * @param preByte  前一个字节
	 * @param nextByte 后一个字节
	 * @return
	 */
	public static int nextAdler32(int oldAdler32 , int chunkSize, byte preByte , byte nextByte ){
		int oldA = oldAdler32 & 0xFFFF ;
		int oldB = (oldAdler32 >>> 16) & 0xFFFF;
		int A = oldA - preByte + nextByte;  //向右移动一字节
		int B = oldB - preByte *  (chunkSize ) -1 + A  ;
		return (B << 16) | A;
	}
	
	/**
	 * 
	 * @param datas 待加密数据块
	 * @return  校验和
	 */
	public static int adler32(byte[] datas){
		return adler32( datas , 0 , datas.length );
	}	
}

package org.example.A_T.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Derek
 * @DateTime: 2021/3/23 13:26
 * @Description: TODO
 */
@Data
@Accessors(chain = true)
public class TrieNode {

    public TrieNode[] nexts;

    public int pass;

    public int end;

    public TrieNode(){
        nexts = new TrieNode[26];
        pass = 0;
        end = 0;
    }

}

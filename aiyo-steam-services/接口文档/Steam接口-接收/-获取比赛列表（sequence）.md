##
1.  接口描述：

2. url： http://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v1

3. 请求参数

    | 字段         | 字段类型 | 是否必填 | 说明   |
    | ---------- | ---- | ---- | ---- |


	start_at_match_seq_num (Optional) (uint64)
	    The match sequence number to start returning results from.
	matches_requested (Optional) (uint32)
	    The amount of matches to return.

4. 返回说明(来自官方文档)



	result

	    status
	        1 - Success
	        8 - 'matches_requested' must be greater than 0.
	
	    statusDetail
	        A message explaining the status, should status not be 1.
	    matches
	        A list of matches.
	
	        See WebAPI/GetMatchDetails the structure of each match.


5. 返回实例
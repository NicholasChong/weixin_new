package com.dongzeviva.weixin.analyze;

public interface SemanticAnalyze {

	public abstract SemanticAnalyzeResult analyze(String word) throws Exception;
	
}

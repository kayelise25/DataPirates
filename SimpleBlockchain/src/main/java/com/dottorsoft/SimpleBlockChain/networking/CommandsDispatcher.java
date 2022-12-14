package com.dottorsoft.SimpleBlockChain.networking;

import com.dottorsoft.SimpleBlockChain.util.ChainUtils;
import com.dottorsoft.SimpleBlockChain.util.Commands;
import com.dottorsoft.SimpleBlockChain.util.Parameters;
import com.dottorsoft.SimpleBlockChain.util.StringUtil;

import wallet.CDClientUI;

public class CommandsDispatcher {
	
	private static CommandsDispatcher dispatcher = null;
	
	private CommandsDispatcher(){}
	
	public static CommandsDispatcher getInstance(){
		
		if(dispatcher == null){
			dispatcher = new CommandsDispatcher();
		}
		
		return dispatcher;
	}
	
	public String elaborateCommands(String command){
		
		if(command == null) return null;
		
		if(command.equals(Commands.GET_BLOCKCHAIN.getCommand())){return StringUtil.getJson(Parameters.blockchain);}
		if(command.equals(Commands.POST_LAST_MINED_BLOCK.getCommand())){return StringUtil.getJson(ChainUtils.getLastBlock());}
		if(command.equals(Commands.GET_BLOCK_CHAIN_SIZE.getCommand())){return StringUtil.getJson(Parameters.blockchain.size());}
		if(command.equals(Commands.PING.getCommand())){return StringUtil.getJson(Commands.PONG.getCommand());}
                if(command.equals(Commands.GET_TRANSACTION.getCommand())){return StringUtil.getJson(Parameters.UTXOs);}

           		 //Get Public Key Testing Purposes
                if(command.equals(Commands.GET_PUBLICKEY.getCommand())){
                    if(Parameters.pubKey == null){
                        Parameters.pubKey = CDClientUI.walletA.getPublicKey();
                        return Parameters.pubKey;
                    }
                    else{
                        System.out.println(Parameters.pubKey);
                    }
                        
                }     
		
		return Commands.UNKNOWN_COMMAND.getCommand();
	}

}

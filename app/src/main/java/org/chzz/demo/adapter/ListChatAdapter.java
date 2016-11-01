package org.chzz.demo.adapter;

import android.content.Context;
import android.view.View;

import org.chzz.adapter.CHZZAdapterViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.demo.R;
import org.chzz.demo.model.ChatModel;


/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/21 上午12:39
 * 描述:
 */
public class ListChatAdapter extends CHZZAdapterViewAdapter<ChatModel> {

    public ListChatAdapter(Context context) {
        super(context, R.layout.item_chat);
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper helper) {
    }

    @Override
    public void fillData(CHZZViewHolderHelper helper, int position, ChatModel model) {
        if (model.mUserType == ChatModel.UserType.From) {
            helper.setVisibility(R.id.rl_item_chat_to, View.GONE);
            helper.setVisibility(R.id.rl_item_chat_from, View.VISIBLE);
            String htmlMsg = String.format(mContext.getString(R.string.color_msg_from), model.mMsg);
            helper.setHtml(R.id.tv_item_chat_from_msg, htmlMsg);
        } else {
            helper.setVisibility(R.id.rl_item_chat_from, View.GONE);
            helper.setVisibility(R.id.rl_item_chat_to, View.VISIBLE);
            String htmlMsg = String.format(mContext.getString(R.string.color_msg_to), model.mMsg);
            helper.setHtml(R.id.tv_item_chat_to_msg, htmlMsg);
        }
    }

}
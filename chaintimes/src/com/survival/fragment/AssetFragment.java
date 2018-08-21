package com.survival.fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cry.library.base.BaseFragment;
import com.cry.library.manager.HttpManager.OnHttpResponseListener;
import com.cry.library.util.JSON;
import com.cry.library.util.Log;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.survival.application.ChainApplication;
import com.survival.chaintimes.AssetChoiceTokenActivity;
import com.survival.chaintimes.LoginActivity;
import com.survival.chaintimes.R;
import com.survival.model.JsonResult;
import com.survival.model.User;
import com.survival.model.Wallet;
import com.survival.utils.Constant;
import com.survival.utils.HttpRequest;

/**
 * 资产
 * @author Survival
 *
 */
public class AssetFragment extends BaseFragment implements OnChartValueSelectedListener, OnClickListener {
	
	private PieChart mChart;
	private ImageView img_choice_token;
	private LinearLayout ll_token;
	private LayoutInflater inflater;
	
	private ChainApplication chainApplication;
	private User user;
	private ArrayList<Wallet> array;
	
	private static final int choice_result = 5;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle savedInstanceState){
		super.onCreateView(inflater, group, savedInstanceState);
		setContentView(R.layout.fragment_asset);
		
		initView();
		initData();
		initEvent();
		
		return view;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		mChart = findViewById(R.id.chart1);
		img_choice_token = findViewById(R.id.img_choice_token);
		ll_token = findViewById(R.id.ll_token);
		inflater = LayoutInflater.from(getActivity());
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		array = new ArrayList<Wallet>();
		
		chainApplication = (ChainApplication) getActivity().getApplicationContext();
		user = chainApplication.getUserInfo();
		
		getWallet();
		
		// 设置 pieChart 图表基本属性
		mChart.setUsePercentValues(false);            //使用百分比显示
		mChart.getDescription().setEnabled(false);    //设置pieChart图表的描述
		mChart.setBackgroundColor(getResources().getColor(R.color.white));      //设置pieChart图表背景色
		mChart.setExtraOffsets(5, 10, 60, 10);        //设置pieChart图表上下左右的偏移，类似于外边距
		mChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]
		mChart.setRotationAngle(0);                   //设置pieChart图表起始角度
		mChart.setRotationEnabled(true);              //设置pieChart图表是否可以手动旋转
		mChart.setHighlightPerTapEnabled(true);       //设置piecahrt图表点击Item高亮是否可用
		mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);// 设置pieChart图表展示动画效果
		 
		// 设置 pieChart 图表Item文本属性
		mChart.setDrawEntryLabels(true);              //设置pieChart是否只显示饼图上百分比不显示文字（true：下面属性才有效果）
		mChart.setEntryLabelColor(Color.WHITE);       //设置pieChart图表文本字体颜色
		//mChart.setEntryLabelTypeface(mTfRegular);     //设置pieChart图表文本字体样式
		mChart.setEntryLabelTextSize(10f);            //设置pieChart图表文本字体大小
		 
		// 设置 pieChart 内部圆环属性
		mChart.setDrawHoleEnabled(true);              //是否显示PieChart内部圆环(true:下面属性才有意义)
		mChart.setHoleRadius(40f);                    //设置PieChart内部圆的半径(这里设置28.0f)
		mChart.setTransparentCircleRadius(31f);       //设置PieChart内部透明圆的半径(这里设置31.0f)
		mChart.setTransparentCircleColor(Color.BLACK);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
		mChart.setTransparentCircleAlpha(50);         //设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
		mChart.setHoleColor(Color.WHITE);             //设置PieChart内部圆的颜色
		mChart.setDrawCenterText(false);               //是否绘制PieChart内部中心文本（true：下面属性才有意义）
		//mChart.setCenterTextTypeface(mTfLight);       //设置PieChart内部圆文字的字体样式
		mChart.setCenterText("Test");                 //设置PieChart内部圆文字的内容
		mChart.setCenterTextSize(10f);                //设置PieChart内部圆文字的大小
		mChart.setCenterTextColor(Color.RED);         //设置PieChart内部圆文字的颜色
		 
		// pieChart添加数据
		setData();
		 
		// 获取pieCahrt图列
		Legend l = mChart.getLegend();
		l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义）
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);  
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		l.setOrientation(Legend.LegendOrientation.VERTICAL);
		l.setForm(Legend.LegendForm.DEFAULT); //设置图例的形状
		l.setFormSize(10);					  //设置图例的大小
		l.setFormToTextSpace(10f);			  //设置每个图例实体中标签和形状之间的间距
		l.setDrawInside(false);
		l.setWordWrapEnabled(true);			  //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
		l.setXEntrySpace(10f);				  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
		l.setYEntrySpace(8f);				  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
		l.setYOffset(0f);					  //设置比例块Y轴偏移量
		l.setTextSize(14f);					  //设置图例标签文本的大小
		l.setTextColor(getResources().getColor(R.color.gray_3));//设置图例标签文本的颜色
		 
		//pieChart 选择监听
		mChart.setOnChartValueSelectedListener(this);
		 
//		//设置MARKERVIEW
//		CustomMarkerView mv = new CustomMarkerView(this, new PercentFormatter());
//		mv.setChartView(mChart);
//		mChart.setMarker(mv);

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		img_choice_token.setOnClickListener(this);

	}

	@Override
	public void onValueSelected(Entry e, Highlight h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected() {
		// TODO Auto-generated method stub
		
	}
	/**
	  * 设置饼图的数据
	  */
	private void setData() {
		ArrayList<PieEntry> pieEntryList = new ArrayList<PieEntry>();
		ArrayList<Integer> colors = new ArrayList<Integer>();
		colors.add(Color.parseColor("#7d55f4"));
		colors.add(Color.parseColor("#33cde5"));//f5585e
		colors.add(Color.parseColor("#f5585e"));//f5585e
		//饼图实体 PieEntry
		PieEntry CashBalance = new PieEntry(25, "理财");
		PieEntry ConsumptionBalance = new PieEntry(30, "ETH");
		PieEntry TBT = new PieEntry(45, "TBT");
		pieEntryList.add(CashBalance);
		pieEntryList.add(ConsumptionBalance);
		pieEntryList.add(TBT);
		//饼状图数据集 PieDataSet
		PieDataSet pieDataSet = new PieDataSet(pieEntryList, "资产分布");
		pieDataSet.setSliceSpace(0f);           //设置饼状Item之间的间隙
		pieDataSet.setSelectionShift(10f);      //设置饼状Item被选中时变化的距离
		pieDataSet.setColors(colors);           //为DataSet中的数据匹配上颜色集(饼图Item颜色)
		//最终数据 PieData
		PieData pieData = new PieData(pieDataSet);
		pieData.setDrawValues(false);            //设置是否显示数据实体(百分比，true:以下属性才有意义)
		pieData.setValueTextColor(Color.BLUE);  //设置所有DataSet内数据实体（百分比）的文本颜色
		pieData.setValueTextSize(12f);          //设置所有DataSet内数据实体（百分比）的文本字体大小
		//pieData.setValueTypeface(mTfLight);     //设置所有DataSet内数据实体（百分比）的文本字体样式
		pieData.setValueFormatter(new PercentFormatter());//设置所有DataSet内数据实体（百分比）的文本字体格式
		mChart.setData(pieData);
		mChart.highlightValues(null);
		mChart.invalidate();                    //将图表重绘以显示设置的属性和数据
	}

	private void getWallet(){
		if(user ==null){
			return;
		}
		showProgressDialog(R.string.dialog_progress);
		HttpRequest.Wallet(user.getUser_code(), 1, new OnHttpResponseListener(){

			@Override
			public void onHttpResponse(int requestCode, String resultJson,Exception e) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				if(e !=null){
					showShortToast(getResources().getString(R.string.result_fail));
					return;
				}

				if(requestCode !=1){
					showShortToast(getResources().getString(R.string.result_error));
					return;
				}
				if(!JSON.isJsonCorrect(resultJson)){
					showShortToast(getResources().getString(R.string.result_error));
					return;
				}
				JsonResult result = JSON.parseObject(resultJson, JsonResult.class);
				if(result ==null){
					showShortToast(getResources().getString(R.string.result_fail));
					return;
				}
				if(result.getCode() == Constant.Config.RESULT_TOKEN_ERROR){
					chainApplication.saveUserInfo(new User());
					startActivity(new Intent(getActivity(), LoginActivity.class));
					getActivity().finish();
					return;
				}
				if(result.getCode() != Constant.Config.RESULT_SUCCESS){
					showShortToast(getResources().getString(R.string.result_fail));
					return;
				}
				Gson gson = new Gson();
				array = gson.fromJson(result.getData(),new TypeToken<ArrayList<Wallet>>(){}.getType());
				ViewPager viewPager = new ViewPager();
				for (Wallet wallet : array) {
					View token = inflater.inflate(R.layout.layout_assets_token_item, null);
					token.setBackgroundDrawable(getResources().getDrawable(R.drawable.asset_token_item));
					viewPager.tv_token_balanceval = token.findViewById(R.id.tv_token_balanceval);
					viewPager.tv_token_rmb_val = token.findViewById(R.id.tv_token_rmb_val);
					viewPager.tv_token_name = token.findViewById(R.id.tv_token_name);
					
					if(viewPager.tv_token_balanceval != null)
					{
						viewPager.tv_token_balanceval.setText(wallet.getCoin_balance());
						viewPager.tv_token_name.setText(wallet.getCoin_name());
						viewPager.tv_token_rmb_val.setText(wallet.getCoin_balance());
					}
					
					ll_token.addView(token);
				}
			}
			
		});
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.img_choice_token:
			Intent intent = new Intent(getActivity(), AssetChoiceTokenActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("wallet", (Serializable) array);
			intent.putExtras(bundle);
			startActivityForResult(intent, choice_result);
			break;

		default:
			break;
		}
	}
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.d("add",Integer.toString(resultCode));
		if(resultCode == Constant.RESULTCODESUCCESS){
			for(int i=0;i<ll_token.getChildCount();i++){
				View view = ll_token.getChildAt(i);
				ll_token.removeView(view);
			};
			initData();
		}
	}
	public class ViewPager{
		private TextView tv_token_balanceval;
		private TextView tv_token_rmb_val;
		private TextView tv_token_name;
	}

}

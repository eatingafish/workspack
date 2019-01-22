package com.example.http;

import com.example.bean.Bitmapbean;
import com.example.bean.CircleBean;
import com.example.bean.GoosMessage.GoodsMessageBean;
import com.example.bean.LoginReg.LoginInfo;
import com.example.bean.TalkBean;
import com.example.bean.dingdan.DetailList;
import com.example.bean.dingdan.OrderList;
import com.example.bean.mypage.Addaddressbean;
import com.example.bean.mypage.MyCirclebean;
import com.example.bean.mypage.WalletBean;
import com.example.bean.searchgoods.BottomBean;
import com.example.bean.searchgoods.SearchGoods;
import com.example.bean.searchgoods.TopBean;
import com.example.bean.shopcart.ShopCartgoods;
import com.example.bean.showbean.ChanceBean;
import com.example.bean.showbean.HomeBean;
import com.example.bean.Result;
import com.example.bean.showbean.MessageBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IRequest {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<LoginInfo>> register(@Field("phone") String mob, @Field("pwd") String pws);

    /**
     * 注册
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> reguser(@Field("phone") String mobile, @Field("pwd") String password);

    /**
     * Banner
     *
     * @return
     */
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<Bitmapbean>>> bitmap();

    /**
    展示数据
     */
    @GET("commodity/v1/commodityList")
    Observable<Result<HomeBean<List<ChanceBean<List<MessageBean>>>>>> home();


    /**
     * 展示圈子数据
     */
    @GET("circle/v1/findCircleList")
    Observable<Result<List<CircleBean>>> circle(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);

    /**
     * 商品詳情中的評論
     */
    @GET("commodity/v1/CommodityCommentList")
    Observable<Result<List<TalkBean>>> reviews(@Query("commodityId") int commodityId, @Query("page") int page, @Query("count") int count);

    /**
     * 商品详情
     */
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<Result<GoodsMessageBean>> detail(@Query("commodityId") int commodityId);

    /**
     * 我的钱包
     */
    @GET("user/verify/v1/findUserWallet")
    Observable<Result<WalletBean>> wallet(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                          @Query("page") int page, @Query("count") int count);

    /**
     * 新增收货地址
     */
    @POST("user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Observable<Result> addAddress(@Header("userId") int userId, @Header("sessionId") String sessionId,
                                  @Field("realName") String realName, @Field("phone") String phone,
                                  @Field("address") String address, @Field("zipCode") String zipCode);

    /**
     * 查询地址列表
     */
    @GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<Addaddressbean>>> showaddress(@Header("userId") int userId, @Header("sessionId") String sessionId);

    /**
     * 根据关键词查找商品
     */
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchGoods>>> searchbykey(@Query("keyword") String keyword, @Query("page") int page,
                                                      @Query("count") int count);

    /**
     * 获取第一层的数据
     *
     * @return
     */
    @GET("commodity/v1/findFirstCategory")
    Observable<Result<List<TopBean>>> top();

    /**
     * 获取第二层的数据
     *
     * @param firstCategoryId
     * @return
     */
    @GET("commodity/v1/findSecondCategory")
    Observable<Result<List<BottomBean>>> bottom(@Query("firstCategoryId") String firstCategoryId);

    /**
     * 点击第二层的图片 搜索
     *
     * @param categoryId
     * @param page
     * @param count
     * @return
     */
    @GET("commodity/v1/findCommodityByCategory")
    Observable<Result<List<SearchGoods>>> btSearch(@Query("categoryId") String categoryId, @Query("page") String page, @Query("count") String count);

    /**
     * 展示购物车数据车
     */
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<ShopCartgoods>>> shopgoods(@Header("userId") long userId,
                                                      @Header("sessionId") String sessionId);
    /**
     * 同步购物车
     */
    @FormUrlEncoded
    @PUT("order/verify/v1/syncShoppingCart")
    Observable<Result> join(@Header("userId") long userId,
                            @Header("sessionId") String sessionId,
                            @Field("data") String data
                            );

    /**
     * 根据订单状态查询订单信息
     */
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Result<List<OrderList<List<DetailList>>>>> allOder(@Header("userId") long userId,
                                                                  @Header("sessionId") String sessionId,
                                                                  @Query("status") int status,
                                                                  @Query("page") int page,
                                                                  @Query("count") int count
                                                                  );
    /**
     * 圈子点赞
     * @param userId
     * @param sessionId
     * @param circleId
     * @return
     */
    @FormUrlEncoded
    @POST("circle/verify/v1/addCircleGreat")
    Observable<Result> agree(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("circleId") int circleId);

    /**
     * 取消圈子点赞
     * @param userId
     * @param sessionId
     * @param circleId
     * @return
     */
    @DELETE("circle/verify/v1/cancelCircleGreat")
    Observable<Result> notagree(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("circleId") int circleId);

    /**
     * 发布圈子
     * @param userId
     * @param sessionId
     * @param body
     * @return
     */
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> fabuquanzi(@Header("userId") int userId, @Header("sessionId") String sessionId,@Body MultipartBody body);

    /**
     * 修改昵称
     */
    @PUT("user/verify/v1/modifyUserNick")
    Observable<Result> updatename(@Header("userId") int userId, @Header("sessionId") String sessionId,@Body String nickName);

    /**
     *支付
      */
    @FormUrlEncoded
    @POST("order/verify/v1/pay")
    Observable<Result> zhifu(@Header("userId") long userId,
                             @Header("sessionId") String sessionId,
                             @Field("orderId") String orderId,
                             @Field("payType") int payType);

    /**
     * 我的圈子
     */
    @GET("circle/verify/v1/findMyCircleById")
    Observable<Result<List<MyCirclebean>>> quanzi(@Header("userId") long userId,
                                            @Header("sessionId") String sessionId,
                                            @Query("page") int page,
                                            @Query("count") int count
                                            );
}

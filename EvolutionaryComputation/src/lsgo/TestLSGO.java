package lsgo;


import java.util.ArrayList;
import java.util.List;
import Function.*;
import evolution.Algorithm;
import evolution.individual.RealSpace;
import evolution.individual.Space;
import evolution.operators.LinearXOver;
import evolution.operators.Operator;
import evolution.operators.UniformMutation;
import evolution.replacements.Generational;
import evolution.replacements.Replacement;
import evolution.selectors.Roulette;
import evolution.selectors.Selector;

public class TestLSGO {
	
	public static void main(String args[]){
		double y[] = new double[1000];
		y[0]=-45.3980021450393;
		y[1]=-30.3779788531999;
		y[2]=25.6630960988;
		y[3]=1.61700556951126;
		y[4]=25.0946745700273;
		y[5]=86.0425212392804;
		y[6]=30.6044661133663;
		y[7]=74.0787654216556;
		y[8]=17.4132769891807;
		y[9]=4.2085335457131;
		y[10]=-81.2731377355566;
		y[11]=-73.4846554550396;
		y[12]=66.4850114412688;
		y[13]=19.4507917236823;
		y[14]=-5.6848756848313;
		y[15]=-17.2532036034884;
		y[16]=72.9540665573115;
		y[17]=56.087899114751;
		y[18]=48.8679207942219;
		y[19]=-79.2171738943308;
		y[20]=-93.2803756824802;
		y[21]=6.41325915818316;
		y[22]=-94.177498741856;
		y[23]=-38.9105969385554;
		y[24]=-46.32681240955;
		y[25]=96.6842830967142;
		y[26]=39.7069490336246;
		y[27]=19.0575632016497;
		y[28]=-55.959797711697;
		y[29]=18.1186688550102;
		y[30]=59.5680177738538;
		y[31]=-95.8187780633773;
		y[32]=-69.809138105542;
		y[33]=-41.3969362435842;
		y[34]=35.9234762742511;
		y[35]=-32.6074227066127;
		y[36]=7.83052373717337;
		y[37]=84.9505575629751;
		y[38]=8.98349343991729;
		y[39]=-5.70745524167187;
		y[40]=64.1426414492716;
		y[41]=55.7503719772029;
		y[42]=93.7390901581876;
		y[43]=77.751698258612;
		y[44]=-72.0120090456449;
		y[45]=43.960712264505;
		y[46]=89.2895055992014;
		y[47]=-70.7445470630081;
		y[48]=85.8468484912868;
		y[49]=13.3404686872725;
		y[50]=-10.1674211156898;
		y[51]=27.8622561185747;
		y[52]=27.027121046412;
		y[53]=-29.869187596935;
		y[54]=8.95918963621997;
		y[55]=-64.7092935343061;
		y[56]=-9.16472262235855;
		y[57]=14.6859564806422;
		y[58]=-47.9403937452386;
		y[59]=14.950128757683;
		y[60]=-15.918835123111;
		y[61]=-60.3010684297085;
		y[62]=48.7483829866617;
		y[63]=-19.3127859200081;
		y[64]=76.1459630834957;
		y[65]=-80.3268474826923;
		y[66]=73.7642179648872;
		y[67]=81.5422410565291;
		y[68]=-48.8675464899637;
		y[69]=-72.0963644233969;
		y[70]=-13.5033454910715;
		y[71]=47.746556660467;
		y[72]=63.1045993383643;
		y[73]=-89.5619810511603;
		y[74]=29.8788454343416;
		y[75]=27.5127752196042;
		y[76]=-70.5146242735462;
		y[77]=-34.7153054405758;
		y[78]=3.5477402531574;
		y[79]=68.9585258984133;
		y[80]=36.6620188302851;
		y[81]=69.0201281337157;
		y[82]=51.2615873753397;
		y[83]=46.4692851734291;
		y[84]=63.5614911027716;
		y[85]=-38.6188160592776;
		y[86]=-43.466346127672;
		y[87]=3.88597806807442;
		y[88]=81.2761468123084;
		y[89]=27.2881461253506;
		y[90]=-3.15822930111991;
		y[91]=-48.4168430082514;
		y[92]=93.131934628909;
		y[93]=86.202917133063;
		y[94]=-51.9361397119308;
		y[95]=64.438838380383;
		y[96]=83.2057536321596;
		y[97]=38.2890011853127;
		y[98]=7.12710663252616;
		y[99]=-39.9127849468667;
		y[100]=-76.8438270418354;
		y[101]=-11.1285196944652;
		y[102]=48.788692451561;
		y[103]=41.2425825051385;
		y[104]=18.7267798135516;
		y[105]=-48.8017747428142;
		y[106]=-14.8551722646396;
		y[107]=-85.785036498627;
		y[108]=-57.3041554073022;
		y[109]=-56.9853855045657;
		y[110]=86.8872869198536;
		y[111]=89.9680854368735;
		y[112]=-41.4433712347553;
		y[113]=-51.5108548546829;
		y[114]=50.9188468599273;
		y[115]=-6.02171048025784;
		y[116]=79.1528579920988;
		y[117]=3.00980274313701;
		y[118]=93.7804516435075;
		y[119]=43.8866607533438;
		y[120]=-53.3517354235637;
		y[121]=69.1864834925838;
		y[122]=-26.8737686258145;
		y[123]=85.3686333928752;
		y[124]=58.6279307262784;
		y[125]=70.230551361501;
		y[126]=-31.5460578726378;
		y[127]=17.1526276277545;
		y[128]=8.12740171842489;
		y[129]=13.8702294355011;
		y[130]=-10.4449840948492;
		y[131]=-22.1588703116998;
		y[132]=8.81384519590406;
		y[133]=-99.5573287896913;
		y[134]=-30.8650905829335;
		y[135]=72.4548822439278;
		y[136]=-18.7440688923644;
		y[137]=-54.2960587704142;
		y[138]=-92.5487818768268;
		y[139]=50.9184236322951;
		y[140]=17.4583761291753;
		y[141]=-96.6905536976085;
		y[142]=-25.9351269128123;
		y[143]=-22.8271948576033;
		y[144]=-87.7879797374571;
		y[145]=29.3981704977656;
		y[146]=55.3009021033285;
		y[147]=28.3258331462935;
		y[148]=69.1915175763368;
		y[149]=22.8061845633615;
		y[150]=5.2850790052509;
		y[151]=-7.77270806663061;
		y[152]=-48.1229093729606;
		y[153]=-60.2413889565432;
		y[154]=-66.610737899133;
		y[155]=5.45652576877089;
		y[156]=-55.0545799919952;
		y[157]=19.3385046158979;
		y[158]=-74.774202755076;
		y[159]=-78.0643609072945;
		y[160]=84.2739294599886;
		y[161]=-27.4703338600276;
		y[162]=-11.8970678860775;
		y[163]=-36.2355784692247;
		y[164]=27.8168853257957;
		y[165]=74.3338331798409;
		y[166]=16.0686664696902;
		y[167]=-6.99404722047384;
		y[168]=-10.778535866981;
		y[169]=-33.9726542537932;
		y[170]=20.1240836371215;
		y[171]=12.178920332888;
		y[172]=82.3678227447714;
		y[173]=67.6533024214721;
		y[174]=-96.9690696907908;
		y[175]=70.0860220777084;
		y[176]=57.6793270933446;
		y[177]=-33.1265101633392;
		y[178]=50.4567361483553;
		y[179]=17.0375585489423;
		y[180]=-58.0015359379189;
		y[181]=75.0036835487933;
		y[182]=-57.866097874205;
		y[183]=75.040696393601;
		y[184]=10.780256569073;
		y[185]=-64.3547277773226;
		y[186]=60.9988772299045;
		y[187]=-55.2112420412089;
		y[188]=27.4400460665705;
		y[189]=3.73355819094983;
		y[190]=48.165480285194;
		y[191]=-33.3800211384215;
		y[192]=-32.9306570115711;
		y[193]=75.0125659868438;
		y[194]=31.3721961727776;
		y[195]=-50.9100888051692;
		y[196]=83.3971765897093;
		y[197]=44.4727954488263;
		y[198]=16.7592674732396;
		y[199]=22.3478429800343;
		y[200]=20.7123699357314;
		y[201]=-61.3924848026144;
		y[202]=25.9008065326839;
		y[203]=67.0908714946714;
		y[204]=-5.62965514132371;
		y[205]=-34.3486903756542;
		y[206]=-48.7262957139849;
		y[207]=36.8827852483621;
		y[208]=58.5495702467181;
		y[209]=-70.9502982848597;
		y[210]=-38.8119580161566;
		y[211]=55.9161733284814;
		y[212]=83.8741725420415;
		y[213]=-95.8974588831882;
		y[214]=-40.1612133239165;
		y[215]=-43.1318751223909;
		y[216]=-42.1910266291601;
		y[217]=14.8029537730658;
		y[218]=52.9483042707801;
		y[219]=51.6016788525433;
		y[220]=90.6770530069839;
		y[221]=-64.6605585058881;
		y[222]=-75.1423631236314;
		y[223]=-35.7472385888919;
		y[224]=26.1566723156991;
		y[225]=21.8130985054402;
		y[226]=16.7565750635577;
		y[227]=-49.884812988732;
		y[228]=-79.1797283019087;
		y[229]=20.8581538885231;
		y[230]=-44.2147568792299;
		y[231]=-44.8022279377701;
		y[232]=43.717079019515;
		y[233]=-51.5108954559222;
		y[234]=9.58127176179333;
		y[235]=-31.2822431201034;
		y[236]=-19.3028561950644;
		y[237]=6.84769015729814;
		y[238]=-12.17869607407;
		y[239]=17.296708538954;
		y[240]=85.9065578280123;
		y[241]=-65.1026622113512;
		y[242]=5.60082377456482;
		y[243]=-45.6194485754765;
		y[244]=50.0626440144361;
		y[245]=57.3895244425262;
		y[246]=34.8464494157117;
		y[247]=-42.3866679727349;
		y[248]=-49.9605604557006;
		y[249]=-67.1690080497155;
		y[250]=-75.8636605746798;
		y[251]=55.378261143162;
		y[252]=50.9087536457093;
		y[253]=24.6618612435035;
		y[254]=-26.4014860439305;
		y[255]=96.0905871247445;
		y[256]=81.4899791747104;
		y[257]=-24.4441002387014;
		y[258]=-25.1936454510525;
		y[259]=-47.2853313513812;
		y[260]=-62.3350092622716;
		y[261]=-53.9555007651098;
		y[262]=-23.4615562781431;
		y[263]=-41.8323514951546;
		y[264]=25.0709228095126;
		y[265]=54.3026056452054;
		y[266]=70.049317206739;
		y[267]=2.24541403760461;
		y[268]=7.9478206868501;
		y[269]=-27.2559307285463;
		y[270]=-77.2865906446096;
		y[271]=6.09949424294379;
		y[272]=-45.8729435195845;
		y[273]=-68.8223070836656;
		y[274]=-5.18154973486162;
		y[275]=-35.7381000250646;
		y[276]=-6.82993872001297;
		y[277]=77.3817665032527;
		y[278]=86.3238503110684;
		y[279]=-54.2558920689249;
		y[280]=-65.6483915954377;
		y[281]=40.0361680953869;
		y[282]=-88.2473252961234;
		y[283]=85.7975942267064;
		y[284]=5.14162241733127;
		y[285]=-43.700299807569;
		y[286]=-17.1365742132572;
		y[287]=-54.5414346958183;
		y[288]=96.4692614903583;
		y[289]=-37.2740142054205;
		y[290]=-7.85837362645542;
		y[291]=5.97800556259548;
		y[292]=66.036644057893;
		y[293]=8.44268421557442;
		y[294]=8.82389400511158;
		y[295]=-63.774591606845;
		y[296]=-51.0010217310401;
		y[297]=-66.1015615836396;
		y[298]=58.5684822546412;
		y[299]=53.1436474574615;
		y[300]=-14.8607736939334;
		y[301]=-93.6239730345087;
		y[302]=76.0296373144993;
		y[303]=-47.1073338838415;
		y[304]=94.2304927129601;
		y[305]=-83.5547184360297;
		y[306]=-4.2893657330977;
		y[307]=-42.1323030049124;
		y[308]=30.0842691494859;
		y[309]=10.6644366461984;
		y[310]=75.0024238549633;
		y[311]=3.9776265206654;
		y[312]=-84.7971537414903;
		y[313]=-4.42033636533417;
		y[314]=-17.5472824716886;
		y[315]=30.9827273291281;
		y[316]=-59.2177181731861;
		y[317]=-48.6631176697141;
		y[318]=-49.4205443326684;
		y[319]=22.0872169013422;
		y[320]=33.5429182370201;
		y[321]=-75.7120627696832;
		y[322]=41.7841548690016;
		y[323]=-16.5245940382898;
		y[324]=26.6280830940358;
		y[325]=13.3661930625946;
		y[326]=36.8295203969721;
		y[327]=-3.54205553665493;
		y[328]=74.3994585777664;
		y[329]=38.7634071736379;
		y[330]=51.6082257833398;
		y[331]=-67.2547689401705;
		y[332]=37.5601918798904;
		y[333]=98.3681442522199;
		y[334]=53.1526327525507;
		y[335]=71.1571702077467;
		y[336]=79.4645725511187;
		y[337]=27.3676572655626;
		y[338]=48.4504756224408;
		y[339]=-68.0563150336642;
		y[340]=68.832551722697;
		y[341]=-9.2649534832263;
		y[342]=-52.0312223771762;
		y[343]=66.7081445561322;
		y[344]=43.6208625512834;
		y[345]=33.9216157554229;
		y[346]=-30.7833939468703;
		y[347]=-50.4074072109503;
		y[348]=-2.48790625815413;
		y[349]=-85.9774152861214;
		y[350]=26.1955447266221;
		y[351]=-97.5035906190329;
		y[352]=93.7109860293664;
		y[353]=36.5508297538948;
		y[354]=6.65143830858641;
		y[355]=-34.4582595242374;
		y[356]=-18.4875490715462;
		y[357]=68.7174153953445;
		y[358]=22.7882401845408;
		y[359]=-10.5138870076488;
		y[360]=24.2824837498555;
		y[361]=-24.152289014423;
		y[362]=48.4350350945586;
		y[363]=-26.9342249664717;
		y[364]=-82.6053076662424;
		y[365]=-95.3745289645673;
		y[366]=-43.2022748401782;
		y[367]=-16.2648496762826;
		y[368]=3.31216257353003;
		y[369]=-95.2139478301559;
		y[370]=-67.6007616587631;
		y[371]=3.03705692176271;
		y[372]=36.2004210177056;
		y[373]=-76.9442966780354;
		y[374]=-79.581508543867;
		y[375]=-95.5185211271535;
		y[376]=-81.5313780115823;
		y[377]=-19.4737196091772;
		y[378]=-39.1091879263577;
		y[379]=35.9115616183471;
		y[380]=-29.3754225291099;
		y[381]=40.6299228877942;
		y[382]=-70.6943598359021;
		y[383]=72.2383056713251;
		y[384]=-1.64826179717244;
		y[385]=19.6296442160176;
		y[386]=51.2689360950565;
		y[387]=-56.1501055041038;
		y[388]=29.8710831142112;
		y[389]=-31.696770335196;
		y[390]=-99.8976161028205;
		y[391]=79.5670886760209;
		y[392]=-28.0617577205467;
		y[393]=91.7040633147429;
		y[394]=29.0853801419446;
		y[395]=75.95283372995;
		y[396]=-70.8493999122594;
		y[397]=-33.1030549973422;
		y[398]=-80.8995237380112;
		y[399]=-22.8119858854887;
		y[400]=27.240415396853;
		y[401]=65.6622964417926;
		y[402]=-86.2062107935773;
		y[403]=-94.2121176567812;
		y[404]=-94.4456728610244;
		y[405]=-0.433556604549773;
		y[406]=93.7363986918712;
		y[407]=50.6065871950362;
		y[408]=-69.6829730118403;
		y[409]=10.6345415329136;
		y[410]=-72.0362273796864;
		y[411]=67.1941517296201;
		y[412]=78.3696306478521;
		y[413]=-56.4017581832007;
		y[414]=30.2950246988001;
		y[415]=9.17605515187904;
		y[416]=-29.7584856137352;
		y[417]=-23.5356365807815;
		y[418]=-64.118863307526;
		y[419]=-45.3939580294437;
		y[420]=-39.8379369106875;
		y[421]=-93.8143282666223;
		y[422]=-36.5931966159809;
		y[423]=20.9045143180412;
		y[424]=-17.1912465271537;
		y[425]=-94.6986554661704;
		y[426]=-35.8903819038984;
		y[427]=-66.9983027681462;
		y[428]=79.348288213894;
		y[429]=-18.5111999810822;
		y[430]=26.9729927221227;
		y[431]=-87.2255134310994;
		y[432]=-41.4381089836258;
		y[433]=48.1762291185975;
		y[434]=83.9630448797937;
		y[435]=92.7903472999775;
		y[436]=-19.9207795308102;
		y[437]=50.2950970075615;
		y[438]=-63.3249661850668;
		y[439]=71.1830737683081;
		y[440]=54.722156900768;
		y[441]=79.2893706432823;
		y[442]=-61.231770497564;
		y[443]=-90.3881501752484;
		y[444]=-51.3500365604704;
		y[445]=-29.1279670650626;
		y[446]=15.0553660384683;
		y[447]=33.7829517997938;
		y[448]=-77.1117633520352;
		y[449]=56.0213835167877;
		y[450]=-75.4905858344077;
		y[451]=-42.2301405909783;
		y[452]=-10.2429212255636;
		y[453]=66.5411193589104;
		y[454]=2.81186592868914;
		y[455]=69.0369637956364;
		y[456]=2.55039364166559;
		y[457]=-81.57680858411;
		y[458]=59.7321951906542;
		y[459]=9.87470997379413;
		y[460]=-56.8746110432635;
		y[461]=47.2980117689289;
		y[462]=98.1720909224342;
		y[463]=-8.05745053939675;
		y[464]=-71.8394468748273;
		y[465]=74.875287093145;
		y[466]=10.2994085530437;
		y[467]=7.19335910915177;
		y[468]=-26.7517682942144;
		y[469]=27.5038820775541;
		y[470]=62.2962263199171;
		y[471]=-1.12203794281221;
		y[472]=49.1622369809449;
		y[473]=21.4258045260482;
		y[474]=-23.0059668707428;
		y[475]=-26.1706012640427;
		y[476]=89.5367353092092;
		y[477]=96.9839546393374;
		y[478]=62.4075026189855;
		y[479]=-40.7199915568438;
		y[480]=42.2808664917846;
		y[481]=67.8609510396858;
		y[482]=56.5072921471155;
		y[483]=-45.9219215613363;
		y[484]=27.1059036891047;
		y[485]=12.6743323655497;
		y[486]=62.8040826478339;
		y[487]=-25.5985873054933;
		y[488]=92.6705658591099;
		y[489]=19.4034022871536;
		y[490]=-8.75094672848273;
		y[491]=-83.7057364421881;
		y[492]=-33.7719374074069;
		y[493]=19.9989787323367;
		y[494]=-29.3382461022972;
		y[495]=-22.7719050866109;
		y[496]=-7.46708283778819;
		y[497]=85.1351176040921;
		y[498]=-43.9013875787079;
		y[499]=-94.0057519200671;
		y[500]=64.8429608884814;
		y[501]=-24.2327029750544;
		y[502]=-85.0922502562804;
		y[503]=77.9536404893765;
		y[504]=48.2660343414486;
		y[505]=-9.2890113912077;
		y[506]=1.11515986448817;
		y[507]=-71.0565164982913;
		y[508]=-11.1875842148273;
		y[509]=14.6948253837138;
		y[510]=63.6321972581252;
		y[511]=-81.1593308201585;
		y[512]=-33.1851264159577;
		y[513]=18.7578761840057;
		y[514]=-29.6267715359105;
		y[515]=50.3111834084497;
		y[516]=-72.2087657291826;
		y[517]=-64.8431629632931;
		y[518]=-85.7875975046447;
		y[519]=-68.053351083166;
		y[520]=-44.796255617575;
		y[521]=90.1052443289084;
		y[522]=17.0531057911827;
		y[523]=77.8266222128751;
		y[524]=-56.7487407964214;
		y[525]=-75.1049363993654;
		y[526]=48.612528559264;
		y[527]=47.6407455409534;
		y[528]=-25.1385517407382;
		y[529]=-5.29364819790358;
		y[530]=-9.94778270888887;
		y[531]=73.9102384361748;
		y[532]=11.4222548013381;
		y[533]=84.8867924006788;
		y[534]=-99.1028979599315;
		y[535]=98.652787902067;
		y[536]=37.3186822795614;
		y[537]=-34.0900449512081;
		y[538]=72.1044240278065;
		y[539]=-65.1014461367791;
		y[540]=53.8467015567176;
		y[541]=-52.8542133770096;
		y[542]=95.9592853589367;
		y[543]=-59.8119377905649;
		y[544]=-78.7835233349891;
		y[545]=-96.1349398324242;
		y[546]=-96.9711940497323;
		y[547]=65.470525180561;
		y[548]=2.0489863051412;
		y[549]=-93.5370571519672;
		y[550]=13.7939107732015;
		y[551]=90.1438309866518;
		y[552]=-17.2989979825481;
		y[553]=97.1724336304829;
		y[554]=-8.41459344933169;
		y[555]=42.3444125243187;
		y[556]=-94.0835194744523;
		y[557]=-7.67610450208401;
		y[558]=-4.26026384449724;
		y[559]=63.0856194404551;
		y[560]=93.157913824197;
		y[561]=-89.8049215307958;
		y[562]=42.2107393792198;
		y[563]=97.9836070879475;
		y[564]=-85.0693838555658;
		y[565]=-74.9646287285528;
		y[566]=-10.4370607365139;
		y[567]=0.680330810310578;
		y[568]=-87.7586120978376;
		y[569]=-43.4648320666089;
		y[570]=-4.85361103351486;
		y[571]=-1.7633754855156;
		y[572]=-53.1482099102272;
		y[573]=-30.6195396789724;
		y[574]=-53.3178666711893;
		y[575]=38.3703078523488;
		y[576]=88.6699036561814;
		y[577]=-1.09668696409981;
		y[578]=-41.4432532934277;
		y[579]=-20.9167323755689;
		y[580]=87.1688079068395;
		y[581]=96.7457699045306;
		y[582]=-70.6215321322975;
		y[583]=-47.1032349611983;
		y[584]=-16.7073037659877;
		y[585]=-84.0809322889772;
		y[586]=-47.5478658529462;
		y[587]=-39.417544202637;
		y[588]=-60.5167767300774;
		y[589]=-50.2700807358487;
		y[590]=-37.1102729213918;
		y[591]=76.8454912397491;
		y[592]=-82.7534242879916;
		y[593]=70.292553750897;
		y[594]=-72.5591670042605;
		y[595]=-59.3312506726649;
		y[596]=95.8266506367508;
		y[597]=-56.5221637148702;
		y[598]=12.3423485203833;
		y[599]=30.8722044790239;
		y[600]=-4.64938614569183;
		y[601]=78.7673897485891;
		y[602]=-78.2545703011703;
		y[603]=-27.4954313998521;
		y[604]=26.7000287786957;
		y[605]=10.4892354220307;
		y[606]=-3.0068159637046;
		y[607]=76.8107059420471;
		y[608]=8.31385944216351;
		y[609]=36.1508067322825;
		y[610]=78.9966728013966;
		y[611]=46.1809131088094;
		y[612]=-25.0059568042088;
		y[613]=-73.4548940253355;
		y[614]=-72.8808287420582;
		y[615]=-2.83933552932237;
		y[616]=-58.9278513623832;
		y[617]=-0.0143681009061742;
		y[618]=53.8162532616903;
		y[619]=-0.391295132559608;
		y[620]=13.2149677929861;
		y[621]=85.9958794766074;
		y[622]=-56.7086976916353;
		y[623]=49.9192265927046;
		y[624]=12.0045956736429;
		y[625]=31.7748904826655;
		y[626]=37.5997278038631;
		y[627]=72.9138772219867;
		y[628]=83.5421949914298;
		y[629]=-85.7119652754249;
		y[630]=-78.6434381805693;
		y[631]=36.5390617100289;
		y[632]=-22.7915924808525;
		y[633]=38.6187475326279;
		y[634]=37.2897733273652;
		y[635]=16.5566235447372;
		y[636]=-86.6475707169327;
		y[637]=-26.1904921219392;
		y[638]=68.5600852205815;
		y[639]=-7.9404920923415;
		y[640]=95.6740783122675;
		y[641]=-86.6422849630644;
		y[642]=-88.7045263919987;
		y[643]=87.7664090485892;
		y[644]=12.3632167236781;
		y[645]=-39.8292563920441;
		y[646]=-60.6416186398707;
		y[647]=8.93757065863423;
		y[648]=-67.7534378272572;
		y[649]=-80.9421922777445;
		y[650]=-39.3543666817012;
		y[651]=62.4214507077358;
		y[652]=-4.2350657485562;
		y[653]=96.611113069801;
		y[654]=32.2293802880178;
		y[655]=-67.0339088869127;
		y[656]=63.5965323985272;
		y[657]=-79.3736458701405;
		y[658]=24.2494158207564;
		y[659]=88.9311860645488;
		y[660]=-84.2995375382389;
		y[661]=23.942642879455;
		y[662]=-17.348870709955;
		y[663]=88.2936486561211;
		y[664]=55.0133174075413;
		y[665]=24.9650114284515;
		y[666]=-85.164061264607;
		y[667]=-46.8181296907353;
		y[668]=-31.584594453061;
		y[669]=-29.0961259976359;
		y[670]=90.3818846514134;
		y[671]=-15.5518484224796;
		y[672]=6.80734062491039;
		y[673]=65.4128329880522;
		y[674]=-15.2561466064839;
		y[675]=-87.1314812943321;
		y[676]=-50.3606629928162;
		y[677]=-23.1262033059365;
		y[678]=-75.6130553307077;
		y[679]=-85.6349190945407;
		y[680]=45.521738424622;
		y[681]=37.4253330919839;
		y[682]=27.122057045428;
		y[683]=-37.6042549933207;
		y[684]=-1.88462607603045;
		y[685]=-90.9196906407629;
		y[686]=-49.1350708840498;
		y[687]=34.7320242372974;
		y[688]=79.6300182411331;
		y[689]=-7.48992054141916;
		y[690]=-77.2690922430477;
		y[691]=93.1173632039341;
		y[692]=43.2424567697014;
		y[693]=17.8579699570069;
		y[694]=-79.2705549954033;
		y[695]=6.21148011922588;
		y[696]=56.7064549494784;
		y[697]=-30.1945190626516;
		y[698]=61.5309890978726;
		y[699]=-24.4719797276971;
		y[700]=-84.9258715718631;
		y[701]=65.9352746080471;
		y[702]=7.0407858510454;
		y[703]=36.828581767442;
		y[704]=65.25582687138;
		y[705]=95.0538880869011;
		y[706]=-66.705587575033;
		y[707]=-48.2581185262928;
		y[708]=43.507500120688;
		y[709]=-13.4377037126098;
		y[710]=-52.9513189446742;
		y[711]=-68.577968448344;
		y[712]=40.3697473421469;
		y[713]=-44.4545902786374;
		y[714]=21.9219242296264;
		y[715]=70.5637341519628;
		y[716]=58.4838143586092;
		y[717]=-30.896843316978;
		y[718]=13.8384187984801;
		y[719]=-53.2602184573509;
		y[720]=40.0831348359843;
		y[721]=-74.5156234624268;
		y[722]=-25.9619620025713;
		y[723]=87.3577969552841;
		y[724]=73.6697697759562;
		y[725]=82.9945216087938;
		y[726]=83.448635680514;
		y[727]=34.1018721242104;
		y[728]=-11.8438781509769;
		y[729]=43.2132392973767;
		y[730]=-2.1572060306672;
		y[731]=28.9279984640736;
		y[732]=-29.6426910793098;
		y[733]=-60.2959618108012;
		y[734]=-57.9372643567647;
		y[735]=82.8270437810363;
		y[736]=-5.04063433702516;
		y[737]=-2.12517150805425;
		y[738]=0.0717801403019437;
		y[739]=37.8022070944166;
		y[740]=-30.8928600611832;
		y[741]=48.2009185983109;
		y[742]=-20.2003212439607;
		y[743]=-1.4346866839399;
		y[744]=-40.0347454149344;
		y[745]=-22.140037274089;
		y[746]=-66.0915618186096;
		y[747]=-57.7557617707273;
		y[748]=-92.9550281453174;
		y[749]=24.7641329070591;
		y[750]=85.5978583079997;
		y[751]=-34.8568462490641;
		y[752]=-49.5087852359456;
		y[753]=27.0875447173695;
		y[754]=20.9890963752977;
		y[755]=12.242741482738;
		y[756]=-1.6675193660922;
		y[757]=-18.3136224123413;
		y[758]=67.2331689558245;
		y[759]=71.9276789677634;
		y[760]=85.6155850990291;
		y[761]=-86.4663013029915;
		y[762]=-54.1287398029733;
		y[763]=-20.7865984060843;
		y[764]=-52.4576894740035;
		y[765]=-28.3474514829136;
		y[766]=14.38560779184;
		y[767]=46.2095869053068;
		y[768]=68.0584288619759;
		y[769]=25.3937402883841;
		y[770]=41.2045360479628;
		y[771]=-92.5381313253757;
		y[772]=10.2686952042857;
		y[773]=-2.02684267527005;
		y[774]=-94.4700709980062;
		y[775]=-39.9955217788068;
		y[776]=18.6490336423212;
		y[777]=71.3300637770984;
		y[778]=-12.45880265381;
		y[779]=-1.6136801633467;
		y[780]=12.9815745437613;
		y[781]=95.1332653355746;
		y[782]=-10.297134070342;
		y[783]=-42.0564924559199;
		y[784]=34.6158127187882;
		y[785]=-18.2882314714959;
		y[786]=96.6144861769228;
		y[787]=95.1605509036354;
		y[788]=-29.0891088074264;
		y[789]=51.7103257896687;
		y[790]=-88.853262551167;
		y[791]=-34.3958254741197;
		y[792]=26.1300423878612;
		y[793]=16.9423541742549;
		y[794]=-61.3092139347636;
		y[795]=75.9031935052136;
		y[796]=15.9407620085045;
		y[797]=19.9388574200634;
		y[798]=74.3856404179542;
		y[799]=37.3895524649873;
		y[800]=18.806977070049;
		y[801]=-41.8415397084328;
		y[802]=87.8294395603507;
		y[803]=78.1513902349171;
		y[804]=76.8646297985936;
		y[805]=55.2382191207814;
		y[806]=-66.2697212709865;
		y[807]=16.0640901018247;
		y[808]=9.87184644558632;
		y[809]=-60.7258543146198;
		y[810]=13.2789962542054;
		y[811]=2.45381130701949;
		y[812]=-64.1632290025875;
		y[813]=16.7649510857271;
		y[814]=46.6831410081107;
		y[815]=-72.1266124185264;
		y[816]=-69.0817060407323;
		y[817]=-19.0899311615059;
		y[818]=9.22982742604682;
		y[819]=-40.2050204337236;
		y[820]=54.9691027245166;
		y[821]=-51.270601650556;
		y[822]=14.2898128763141;
		y[823]=-7.7050487014653;
		y[824]=-21.7486876707808;
		y[825]=-36.1504940223228;
		y[826]=-57.0389071355736;
		y[827]=-8.6889345512632;
		y[828]=-48.6661206182102;
		y[829]=-39.7024098379071;
		y[830]=82.8264930897825;
		y[831]=-28.4739241404072;
		y[832]=80.1831385895318;
		y[833]=99.7080415983056;
		y[834]=29.3264632517702;
		y[835]=53.7913269696524;
		y[836]=65.1552353796062;
		y[837]=-22.190597159474;
		y[838]=2.76211628097208;
		y[839]=63.1548070124104;
		y[840]=-38.8600416347941;
		y[841]=12.5705820605899;
		y[842]=10.9887294414983;
		y[843]=-97.1143278787549;
		y[844]=50.1809787936434;
		y[845]=16.3675775230557;
		y[846]=-36.7260720762387;
		y[847]=93.7100369996155;
		y[848]=-72.175022121973;
		y[849]=4.52514643862816;
		y[850]=74.892901745793;
		y[851]=19.7246216420384;
		y[852]=-30.961270780365;
		y[853]=16.208520832207;
		y[854]=58.1478972807116;
		y[855]=62.5458105904802;
		y[856]=28.5592264137276;
		y[857]=21.0338999573402;
		y[858]=-8.52394805375143;
		y[859]=-72.9226389129764;
		y[860]=-61.0478710575704;
		y[861]=-52.7613818950213;
		y[862]=-13.1657231866605;
		y[863]=-52.6504582430188;
		y[864]=-59.605803506802;
		y[865]=-83.6061640809784;
		y[866]=20.9307497111883;
		y[867]=16.9228420653251;
		y[868]=59.3382463511685;
		y[869]=19.7214731906295;
		y[870]=-31.8227559801974;
		y[871]=-32.5743456076242;
		y[872]=-22.2514020149186;
		y[873]=25.1037817350799;
		y[874]=-14.9341081028847;
		y[875]=88.8202811133249;
		y[876]=-71.4931857469543;
		y[877]=60.6182544439328;
		y[878]=27.7877061022729;
		y[879]=-47.6029447458813;
		y[880]=89.1059791559099;
		y[881]=-14.5657497450082;
		y[882]=-27.0452328299855;
		y[883]=23.4758560521469;
		y[884]=44.5548704182153;
		y[885]=97.9896954877806;
		y[886]=37.3479360130865;
		y[887]=-5.90239403452445;
		y[888]=31.9037080341141;
		y[889]=-37.813970634287;
		y[890]=69.140531321202;
		y[891]=37.7058653891937;
		y[892]=22.2597564279301;
		y[893]=12.6018625245283;
		y[894]=78.959771646344;
		y[895]=66.9733763189956;
		y[896]=-9.62862464519838;
		y[897]=-24.9931025194067;
		y[898]=95.2293863065881;
		y[899]=-16.6013010793178;
		y[900]=-90.2116846545289;
		y[901]=-39.6110838866429;
		y[902]=-14.6273309493733;
		y[903]=-23.8325065418244;
		y[904]=-29.5090326167996;
		y[905]=92.0565240212899;
		y[906]=20.714910368222;
		y[907]=-68.3926142338334;
		y[908]=81.7800590005154;
		y[909]=-75.4386611671973;
		y[910]=86.8149371123964;
		y[911]=56.9271489561935;
		y[912]=-91.6796423169132;
		y[913]=46.7153221861725;
		y[914]=3.45332412681782;
		y[915]=-8.5059096871458;
		y[916]=-36.9322039555353;
		y[917]=-98.1170745912488;
		y[918]=-9.60952812870392;
		y[919]=74.8436979756072;
		y[920]=-76.7863589379387;
		y[921]=-12.4827418770065;
		y[922]=32.7204540836424;
		y[923]=-67.8549970992196;
		y[924]=22.6605988621554;
		y[925]=60.8396537272709;
		y[926]=-79.8998707556705;
		y[927]=21.2473044618815;
		y[928]=62.7031037925177;
		y[929]=-27.1292534518212;
		y[930]=88.1548270026346;
		y[931]=-20.280030947908;
		y[932]=38.3286184857943;
		y[933]=-63.9630069044396;
		y[934]=-34.7569597475096;
		y[935]=-34.2120827639414;
		y[936]=-82.5221981489871;
		y[937]=-52.8293307719269;
		y[938]=-1.03947950259453;
		y[939]=-14.7907733024921;
		y[940]=71.290801615991;
		y[941]=20.2017599046538;
		y[942]=28.0757289473533;
		y[943]=19.9840299103865;
		y[944]=97.3127551811938;
		y[945]=2.04821085409472;
		y[946]=-91.2350837285963;
		y[947]=33.2037859293756;
		y[948]=69.6531927949554;
		y[949]=-0.233408455639167;
		y[950]=-54.7028721208301;
		y[951]=-23.296880675823;
		y[952]=-21.5725004956895;
		y[953]=-2.24396486654517;
		y[954]=66.0316925541289;
		y[955]=13.2873314174261;
		y[956]=19.1115365116949;
		y[957]=-38.7127313339212;
		y[958]=-95.521654025468;
		y[959]=15.1934434649693;
		y[960]=-3.94432849423731;
		y[961]=21.0656924785712;
		y[962]=80.0504350519019;
		y[963]=65.0358044704033;
		y[964]=-16.8271052580551;
		y[965]=64.9861765581507;
		y[966]=15.3266715851484;
		y[967]=-18.4356788426591;
		y[968]=-58.423216825912;
		y[969]=-43.0227892588192;
		y[970]=82.4728426138258;
		y[971]=-54.9958625340713;
		y[972]=35.6637356220098;
		y[973]=61.6384436502674;
		y[974]=51.2048636365933;
		y[975]=-56.1335999320481;
		y[976]=-93.1264882894455;
		y[977]=-53.0627196697004;
		y[978]=-77.1711995525374;
		y[979]=-14.2499902087295;
		y[980]=-83.3736998441747;
		y[981]=64.3184560924997;
		y[982]=87.5554314555896;
		y[983]=-74.1348829674656;
		y[984]=33.6811984061241;
		y[985]=7.92482069058448;
		y[986]=-59.5526175118632;
		y[987]=-9.50621010587331;
		y[988]=-3.42822975376702;
		y[989]=-74.0433563823271;
		y[990]=-20.4142778581253;
		y[991]=44.3631889038172;
		y[992]=80.9204131593793;
		y[993]=-10.9964539093664;
		y[994]=-61.7173991549223;
		y[995]=-78.2131520072974;
		y[996]=-51.5357154508361;
		y[997]=-46.1705375166646;
		y[998]=50.3080508812872;
		y[999]=0.367837442299788;

		double min = -100;
		double max = 100;
		int DIM = 1000;
		int nPop = 100;
		int maxIterations = 100;
		Space<Double> space = new RealSpace(min, max, DIM);
		Selector selector = new Roulette();
		Replacement<Double> replacement = new Generational();
		List<Operator<Double>> operators = new ArrayList<>();
		operators.add(new UniformMutation());
		operators.add(new LinearXOver());
		Function<Double> f = new ShiftedElliptic(DIM);
		Algorithm<Double> search = new Algorithm<>(nPop, space, selector, replacement, operators, maxIterations, f);
		search.iterate();
		
		
	}

}

package org.ir.project;

import org.ir.project.utils.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URLEncoder;

public class LanguageDetectorTest {
    Helper utilities = new Helper();

    @Test
    public void test_Kannada_Language() throws IOException, InterruptedException {
        String webPageContent = "ನಾನು ಚೆನ್ನಾಗಿದ್ದೇನೆ ನೀನು ಹೇಗಿದ್ದೀಯ ತುಂಬಾ ಒಳ್ಳೆಯದು";
        System.out.println(utilities.getWebPageLanguage(URLEncoder.encode(webPageContent, "UTF-8")));
    }

    @Test
    public void test_Marathi_Language() throws IOException, InterruptedException {
        String webPageContent = "मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल. मराठी विकिपीडिया हा विकिपीडिया या मुक्त ऑनलाईन ज्ञानकोश प्रकल्पातील मराठी भाषेतला ज्ञानकोश आहे. मे १, इ.स. २००३ रोजी मराठी विकिपीडियाची सुरुवात झाली. जुलै, इ.स. २०१६ मध्ये मराठी विकिपीडियाची लेखसंख्या ४२,००० च्यावर जाऊन पोचली. डिसेंबर २०१७ मध्ये मराठी विकिपीडियावर ५०,००० लेख पूर्ण झाले आहेत. शिवाजी महाराज व बाबासाहेब आंबेडकर हे मराठी विकिपीडियावरील सर्वाधिक वाचले जाणारे लेख आहेत विकिपीडियाचे प्रशासक, प्रचालक यांचे मतानुसार विकिपीडियावर लेख लिहिताना त्यातील माहिती अचूकच पाहिजे असा हट्टाग्रह करुन उपयोग नाही. परिणामत: येथील मजकूर अचूकच असेल याची कोणतीही खात्री देता येऊ शकत नाही. तरी येथील माहितीचा वापर करताना त्या माहितीची अन्य काही ठिकाणांहून पडताळणी केलेली निश्चितच उपयोगी ठरेल.";
        String utf_encoded_webPageContent = URLEncoder.encode(webPageContent, "UTF-8");
        //Limiting to the 7500 chars as API accept max 7500 chars
        System.out.println(utilities.getWebPageLanguage(utf_encoded_webPageContent.length() > 7500 ? utf_encoded_webPageContent.substring(0, 7500) : utf_encoded_webPageContent));
    }

    @Test
    public void test_Chines_Language() throws IOException, InterruptedException {
        String webPageContent = "密苏里州百年纪念半美元是美国铸币局1921年铸造的50美分纪念币，由罗伯特·英格索尔·艾特肯设计，旨在纪念密苏里州成立一百周年。建议发行纪念币的法案在国会两院顺利通过，没有议员反对，然后由沃伦·盖玛利尔·哈定总统在1921年3月4日的就职典礼上签字后成为法律正式生效。美国美术委员会聘请艾特肯设计这枚半美元，后者在硬币两面都刻上丹尼尔·布恩的形象，其中背面的布恩身边还站有美洲原住民，很可能象征着白人定居者对印第安人的驱逐。为提升销量，部分纪念币上刻有“2★4”标记，代表密苏里州是第24个加入联邦的州。虽然设计颇受好评，但半美元销售业绩很不理想，近六成最终退回费城铸币局熔毁。如今存世的半美元中带“2★4”标记的占少数，但两者价值差距很小。";
        System.out.println(utilities.getWebPageLanguage(URLEncoder.encode(webPageContent, "UTF-8")));
    }

    @Test
    public void test_Engilsh_Language() throws IOException, InterruptedException {
        String webPageContent = "The Tower Hill Memorial is a pair of Commonwealth War Graves Commission memorials in Trinity Square, on Tower Hill in London, England. The memorials, one for the First World War and one for the Second, commemorate civilian merchant sailors and fishermen who were killed as a result of enemy action and have no known grave. The first, the Mercantile Marine War Memorial, was designed by Sir Edwin Lutyens and unveiled in 1928; the second, the Merchant Seamen's Memorial, was designed by Sir Edward Maufe and unveiled in 1955. A third memorial, commemorating merchant sailors who were killed in the 1982 Falklands War, was added to the site in 2005.";
        System.out.println(utilities.getWebPageLanguage(URLEncoder.encode(webPageContent, "UTF-8")));
    }
}

/*
 * Copyright (c) 2016 Dr. Matthias Wegner
 * 
 * Project Circlead (Circled Thread)
 * 
 * This project realizes a decentralized evolutionary model for
 * organic growing organisations. It can be used to organise and
 * steer the organisational communication and transparence.
 * 
 * DE: Ein Thread ist im englischen ein Gedankengang, ein durchgängiger Faden.
 * Dieser Faden ist kreisförmig aufgewickelt, wodurch die Rollen dargestellt werden. 
 * 
 * All rights reserved under the Creative Commons Licence BY-NC-SA 3.0.
 * You may obtain a copy of the License at
 * 
 *        https://creativecommons.org/licenses/by-nc-sa/3.0/
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rogatio.circlead.util;

/*******************************************************************************
 * Copyright (c) 2010 Haifeng Li
 *   
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


// TODO: Auto-generated Javadoc
/**
 * Color palette generator.
 *
 * @author Haifeng Li
 */
public class ColorPalette {

   /** The Constant WHITE. */
   public static final Color WHITE = Color.WHITE;
   
   /** The Constant BLACK. */
   public static final Color BLACK = Color.BLACK;
   
   /** The Constant LIGHT_GRAY. */
   public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
   
   /** The Constant DARK_GRAY. */
   public static final Color DARK_GRAY = Color.DARK_GRAY;
   
   /** The Constant SLATE_GRAY. */
   public static final Color SLATE_GRAY = new Color(0X708090);
   
   /** The Constant LIGHT_SLATE_GRAY. */
   public static final Color LIGHT_SLATE_GRAY = new Color(0X6D7B8D);
   
   /** The Constant DARK_SLATE_GRAY. */
   public static final Color DARK_SLATE_GRAY = new Color(0X2F4F4F);
   
   /** The Constant RED. */
   public static final Color RED = Color.RED;
   
   /** The Constant DARK_RED. */
   public static final Color DARK_RED = new Color(0X8B0000);
   
   /** The Constant VIOLET_RED. */
   public static final Color VIOLET_RED = new Color(0XF6358A);
   
   /** The Constant GREEN. */
   public static final Color GREEN = Color.GREEN;
   
   /** The Constant DARK_GREEN. */
   public static final Color DARK_GREEN = new Color(0X006400);
   
   /** The Constant LIGHT_GREEN. */
   public static final Color LIGHT_GREEN = new Color(0X90EE90);
   
   /** The Constant PASTEL_GREEN. */
   public static final Color PASTEL_GREEN = new Color(0X00FF00);
   
   /** The Constant FOREST_GREEN. */
   public static final Color FOREST_GREEN = new Color(0X808000);
   
   /** The Constant GRASS_GREEN. */
   public static final Color GRASS_GREEN = new Color(0X408080);
   
   /** The Constant BLUE. */
   public static final Color BLUE = Color.BLUE;
   
   /** The Constant NAVY_BLUE. */
   public static final Color NAVY_BLUE = new Color(0X000080);
   
   /** The Constant SLATE_BLUE. */
   public static final Color SLATE_BLUE = new Color(0X6A5ACD);
   
   /** The Constant ROYAL_BLUE. */
   public static final Color ROYAL_BLUE = new Color(0X2B60DE);
   
   /** The Constant CADET_BLUE. */
   public static final Color CADET_BLUE = new Color(0X4C787E);
   
   /** The Constant MIDNIGHT_BLUE. */
   public static final Color MIDNIGHT_BLUE = new Color(0X151B54);
   
   /** The Constant SKY_BLUE. */
   public static final Color SKY_BLUE = new Color(0X6698FF);
   
   /** The Constant STEEL_BLUE. */
   public static final Color STEEL_BLUE = new Color(0X4863A0);
   
   /** The Constant DARK_BLUE. */
   public static final Color DARK_BLUE = new Color(0X00008B);
   
   /** The Constant MAGENTA. */
   public static final Color MAGENTA = Color.MAGENTA;
   
   /** The Constant DARK_MAGENTA. */
   public static final Color DARK_MAGENTA = new Color(0X8B008B);
   
   /** The Constant CYAN. */
   public static final Color CYAN = Color.CYAN;
   
   /** The Constant DARK_CYAN. */
   public static final Color DARK_CYAN = new Color(0X008B8B);
   
   /** The Constant PURPLE. */
   public static final Color PURPLE = new Color(0XA020F0);
   
   /** The Constant LIGHT_PURPLE. */
   public static final Color LIGHT_PURPLE = new Color(0XFF0080);
   
   /** The Constant DARK_PURPLE. */
   public static final Color DARK_PURPLE = new Color(0X800080);
   
   /** The Constant ORANGE. */
   public static final Color ORANGE = Color.ORANGE;
   
   /** The Constant PINK. */
   public static final Color PINK = Color.PINK;
   
   /** The Constant YELLOW. */
   public static final Color YELLOW = Color.YELLOW;
   
   /** The Constant GOLD. */
   public static final Color GOLD = new Color(0XFFD700);
   
   /** The Constant BROWN. */
   public static final Color BROWN = new Color(0XA52A2A);
   
   /** The Constant SALMON. */
   public static final Color SALMON = new Color(0XFA8072);
   
   /** The Constant TURQUOISE. */
   public static final Color TURQUOISE = new Color(0X00FFFF);
   
   /** The Constant BURGUNDY. */
   public static final Color BURGUNDY = new Color(0X800000);
   
   /** The Constant PLUM. */
   public static final Color PLUM = new Color(0XB93B8F);

   /** The Constant COLORS. */
   public static final Color[] COLORS = { RED, BLUE, GREEN, MAGENTA, CYAN, PURPLE, ORANGE, PINK, YELLOW, GOLD, BROWN, SALMON, TURQUOISE, BURGUNDY, PLUM, DARK_RED, VIOLET_RED, DARK_GREEN, LIGHT_GREEN,
         PASTEL_GREEN, FOREST_GREEN, GRASS_GREEN, NAVY_BLUE, SLATE_BLUE, ROYAL_BLUE, CADET_BLUE, MIDNIGHT_BLUE, SKY_BLUE, STEEL_BLUE, DARK_BLUE, DARK_MAGENTA, DARK_CYAN, LIGHT_PURPLE, DARK_PURPLE,
         LIGHT_GRAY, DARK_GRAY, SLATE_GRAY, LIGHT_SLATE_GRAY, DARK_SLATE_GRAY, BLACK, };

   /**
    * Generate terrain color palette.
    *
    * @param n           the number of colors in the palette.
    * @return the color[]
    */
   public static Color[] terrain(int n) {
      return terrain(n, 1.0f);
   }

   /**
    * Generate terrain color palette.
    *
    * @param n           the number of colors in the palette.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   public static Color[] terrain(int n, float alpha) {
      int k = n / 2;
      float[] H = { 4 / 12f, 2 / 12f, 0 / 12f };
      float[] S = { 1f, 1f, 0f };
      float[] V = { 0.65f, 0.9f, 0.95f };

      Color[] palette = new Color[n];

      float h = H[0];
      float hw = (H[1] - H[0]) / (k - 1);

      float s = S[0];
      float sw = (S[1] - S[0]) / (k - 1);

      float v = V[0];
      float vw = (V[1] - V[0]) / (k - 1);

      for (int i = 0; i < k; i++) {
         palette[i] = hsv(h, s, v, alpha);
         h += hw;
         s += sw;
         v += vw;
      }

      h = H[1];
      hw = (H[2] - H[1]) / (n - k);

      s = S[1];
      sw = (S[2] - S[1]) / (n - k);

      v = V[1];
      vw = (V[2] - V[1]) / (n - k);

      for (int i = k; i < n; i++) {
         h += hw;
         s += sw;
         v += vw;
         palette[i] = hsv(h, s, v, alpha);
      }

      return palette;
   }

   /**
    * Generate topo color palette.
    *
    * @param n           the number of colors in the palette.
    * @return the color[]
    */
   public static Color[] topo(int n) {
      return topo(n, 1.0f);
   }

   /**
    * Generate topo color palette.
    *
    * @param n           the number of colors in the palette.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   public static Color[] topo(int n, float alpha) {
      int j = n / 3;
      int k = n / 3;
      int i = n - j - k;

      Color[] palette = new Color[n];

      float h = 43 / 60.0f;
      float hw = (31 / 60.0f - h) / (i - 1);
      int l = 0;
      for (; l < i; l++) {
         palette[l] = hsv(h, 1.0f, 1.0f, alpha);
         h += hw;
      }

      h = 23 / 60.0f;
      hw = (11 / 60.0f - h) / (j - 1);
      for (; l < i + j; l++) {
         palette[l] = hsv(h, 1.0f, 1.0f, alpha);
         h += hw;
      }

      h = 10 / 60.0f;
      hw = (6 / 60.0f - h) / (k - 1);
      float s = 1.0f;
      float sw = (0.3f - s) / (k - 1);
      for (; l < n; l++) {
         palette[l] = hsv(h, s, 1.0f, alpha);
         h += hw;
         s += sw;
      }

      return palette;
   }

   /**
    * Generate jet color palette.
    *
    * @param n           the number of colors in the palette.
    * @return the color[]
    */
   public static Color[] jet(int n) {
      return jet(n, 1.0f);
   }

   /**
    * Generate jet color palette.
    *
    * @param n           the number of colors in the palette.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   public static Color[] jet(int n, float alpha) {
      int m = (int) Math.ceil(n / 4);

      float[] u = new float[3 * m];
      for (int i = 0; i < u.length; i++) {
         if (i == 0) {
            u[i] = 0.0f;
         } else if (i <= m) {
            u[i] = i / (float) m;
         } else if (i <= 2 * m - 1) {
            u[i] = 1.0f;
         } else {
            u[i] = (3 * m - i) / (float) m;
         }

      }

      int m2 = m / 2 + m % 2;
      int mod = n % 4;
      int[] r = new int[n];
      int[] g = new int[n];
      int[] b = new int[n];
      for (int i = 0; i < u.length - 1; i++) {
         if (m2 - mod + i < n) {
            g[m2 - mod + i] = i + 1;
         }
         if (m2 - mod + i + m < n) {
            r[m2 - mod + i + m] = i + 1;
         }
         if (i > 0 && m2 - mod + i < u.length) {
            b[i] = m2 - mod + i;
         }
      }

      Color[] palette = new Color[n];
      for (int i = 0; i < n; i++) {
         palette[i] = new Color(u[r[i]], u[g[i]], u[b[i]], alpha);
      }

      return palette;
   }

   /**
    * Generate red-green color palette.
    *
    * @param n           the number of colors in the palette.
    * @return the color[]
    */
   public static Color[] redgreen(int n) {
      return redgreen(n, 1.0f);
   }

   /**
    * Generate red-green color palette.
    *
    * @param n           the number of colors in the palette.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   public static Color[] redgreen(int n, float alpha) {
      Color[] palette = new Color[n];
      for (int i = 0; i < n; i++) {
         palette[i] = new Color((float) Math.sqrt((i + 1.0f) / n), (float) Math.sqrt(1 - (i + 1.0f) / n), 0.0f, alpha);
      }

      return palette;
   }

   /**
    * Generate red-blue color palette.
    *
    * @param n           the number of colors in the palette.
    * @return the color[]
    */
   public static Color[] redblue(int n) {
      return redblue(n, 1.0f);
   }

   /**
    * Generate red-blue color palette.
    *
    * @param n           the number of colors in the palette.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   public static Color[] redblue(int n, float alpha) {
      Color[] palette = new Color[n];
      for (int i = 0; i < n; i++) {
         palette[i] = new Color((float) Math.sqrt((i + 1.0f) / n), 0.0f, (float) Math.sqrt(1 - (i + 1.0f) / n), alpha);
      }

      return palette;
   }

   /**
    * Generate heat color palette.
    *
    * @param n           the number of colors in the palette.
    * @return the color[]
    */
   public static Color[] heat(int n) {
      return heat(n, 1.0f);
   }

   /**
    * Generate heat color palette.
    *
    * @param n           the number of colors in the palette.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   public static Color[] heat(int n, float alpha) {
      int j = n / 4;
      int k = n - j;
      float h = 1.0f / 6;

      Color[] c = rainbow(k, 0, h, alpha);

      Color[] palette = new Color[n];
      System.arraycopy(c, 0, palette, 0, k);

      float s = 1 - 1.0f / (2 * j);
      float end = 1.0f / (2 * j);
      float w = (end - s) / (j - 1);

      for (int i = k; i < n; i++) {
         palette[i] = hsv(h, s, 1.0f, alpha);
         s += w;
      }

      return palette;
   }

   /**
    * Generate rainbow color palette.
    *
    * @param n           the number of colors in the palette.
    * @return the color[]
    */
   public static Color[] rainbow(int n) {
      return rainbow(n, 1.0f);
   }

   /**
    * Generate rainbow color palette.
    *
    * @param n           the number of colors in the palette.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   public static Color[] rainbow(int n, float alpha) {
      return rainbow(n, 0.0f, (float) (n - 1) / n, alpha);
   }

   /**
    * Generate rainbow color palette.
    *
    * @param n           the number of colors in the palette.
    * @param start           the start of h in the HSV color model.
    * @param end           the start of h in the HSV color model.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   private static Color[] rainbow(int n, float start, float end, float alpha) {
      return rainbow(n, start, end, 1.0f, 1.0f, alpha);
   }

   /**
    * Generate rainbow color palette.
    *
    * @param n           the number of colors in the palette.
    * @param start           the start of h in the HSV color model.
    * @param end           the start of h in the HSV color model.
    * @param s           the s in the HSV color model.
    * @param v           the v in the HSV color model.
    * @param alpha           the parameter in [0,1] for transparency.
    * @return the color[]
    */
   private static Color[] rainbow(int n, float start, float end, float s, float v, float alpha) {
      Color[] palette = new Color[n];
      float h = start;
      float w = (end - start) / (n - 1);
      for (int i = 0; i < n; i++) {
         palette[i] = hsv(h, s, v, alpha);
         h += w;
      }

      return palette;
   }

   /**
    * Generate a color based on HSV model.
    *
    * @param h the h
    * @param s the s
    * @param v the v
    * @param alpha the alpha
    * @return the color
    */
   private static Color hsv(float h, float s, float v, float alpha) {
      float r = 0;
      float g = 0;
      float b = 0;

      if (s == 0) {
         // this color in on the black white center line <=> h = UNDEFINED
         // Achromatic color, there is no hue
         r = v;
         g = v;
         b = v;
      } else {
         if (h == 1.0f) {
            h = 0.0f;
         }

         // h is now in [0,6)
         h *= 6;

         int i = (int) Math.floor(h);
         float f = h - i; // f is fractional part of h
         float p = v * (1 - s);
         float q = v * (1 - (s * f));
         float t = v * (1 - (s * (1 - f)));

         switch (i) {
         case 0:
            r = v;
            g = t;
            b = p;
            break;

         case 1:
            r = q;
            g = v;
            b = p;
            break;

         case 2:
            r = p;
            g = v;
            b = t;
            break;

         case 3:
            r = p;
            g = q;
            b = v;
            break;

         case 4:
            r = t;
            g = p;
            b = v;
            break;

         case 5:
            r = v;
            g = p;
            b = q;
            break;

         }
      }

      return new Color(r, g, b, alpha);
   }
   
   /** The Constant rainbowColors. */
   private final static List<Color> rainbowColors = Arrays.asList(ColorPalette.rainbow(65536));
   
   /** The Constant rainbowColorsHtml. */
   private final static List<String> rainbowColorsHtml = convertToHtmlColors(ColorPalette.rainbow(65536));
   
   /**
    * Convert to html colors.
    *
    * @param colors the colors
    * @return the list
    */
   public static List<String> convertToHtmlColors(Color[] colors) {
      List<String> html = new ArrayList<String>(); 
      for (Color color : rainbowColors) {
         html.add(ObjectUtil.convertToHtmlColor(color));  
      }
      return html;
   }
   
   /**
    * Gets the order index.
    *
    * @param color the color
    * @return the order index
    */
   public static int getOrderIndex(String color) {
      int idx = rainbowColorsHtml.indexOf(color);
      
      if (idx!=-1) {
         return (int)(idx*SCALEFACTOR);
      }
      
      int distance = 10;
      for (String c : rainbowColorsHtml) {
         int tmpDistance = StringUtils.getLevenshteinDistance(c, color);
         if (tmpDistance<distance) {
            distance = tmpDistance;
            idx = rainbowColorsHtml.indexOf(c);
         }
      }
      
      return (int)(idx*SCALEFACTOR);
   }
   
   /**
    * Gets the order index.
    *
    * @param color the color
    * @return the order index
    */
   public static int getOrderIndex(Color color) {
      return rainbowColors.indexOf(color);
   }
   
   /** The Constant SCALEFACTOR. */
   public static final double SCALEFACTOR = 1.25;
}
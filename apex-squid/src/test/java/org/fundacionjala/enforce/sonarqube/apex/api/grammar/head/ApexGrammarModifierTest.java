/*
 * The MIT License
 *
 * Copyright 2016 Jalasoft.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.fundacionjala.enforce.sonarqube.apex.api.grammar.head;

import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.RuleKey.LOOKAHEAD_KEYWORD;
import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.RuleKey.MODIFIER;
import org.junit.Test;
import org.sonar.sslr.grammar.LexerlessGrammarBuilder;
import static org.sonar.sslr.tests.Assertions.assertThat;

public class ApexGrammarModifierTest {

    private final LexerlessGrammarBuilder grammarBuilder = ApexGrammarModifier.createGrammarBuilder();


    @Test
    public void buildingModifierDifferentCorrectCasesLookaheadKeyword() {
        assertThat(grammarBuilder.build().rule(LOOKAHEAD_KEYWORD))
                .matches("publicwithsharing")
                .matches("finalwithoutsharing")
                .matches("abstractwithsharing")
                .matches("synchronizedwithsharing")
                .matches("nativewithsharing")
                .matches("transientwithsharing");
    }
    
    @Test
    public void buildingModifierWithOnlyCasesLookahead() {
        assertThat(grammarBuilder.build().rule(MODIFIER))
                .matches("static")
                .matches("public")
                .matches("final")
                .matches("abstract")
                .matches("synchronized")
                .matches("native")
                .matches("transient")
                .matches("volatile")
                .matches("strictfp")
                .matches("anotation");
    }
    
    @Test
    public void buildingModifierDifferentCorrectCases() {
        assertThat(grammarBuilder.build().rule(MODIFIER))
                .matches("static")
                .matches("publicwithsharing")
                .matches("finalwithoutsharing")
                .matches("abstractwithsharing")
                .matches("synchronizedwithsharing")
                .matches("nativewithsharing")
                .matches("transientwithsharing");
    }

    @Test
    public void buildingModifierDifferentIncorrectCases() {
        assertThat(grammarBuilder.build().rule(MODIFIER))
                .notMatches("_")
                .notMatches("static _")
                .notMatches("public With sharing")
                .notMatches("final  without Sharing")
                .notMatches("  _abstract with sharing")
                .notMatches("synchronized_with sharing")
                .notMatches("  transient with sharing_  ");
    }

}

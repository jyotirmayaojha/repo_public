// Program  -> main () {  declarations   statement-list }
// declarations-> data-type identifier-list; declarations | epsilon
// data-type -> int|char
// identifier-list -> id|id, identifier-list
// statement_list -> statement statement_list | epsilon
// statement -> assign-stat;
// assign_stat -> id = expn

#include <iostream>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

int india=0;
char command[1024];
char str[200];
char mat[200][200];
int kx=-1;

int j=0;
char sec[2];
char sc;
struct node
{
	char  type[1024];
	char  val[1024];
	node * next;
	node *prev;
};
typedef struct node * ptr; 
char put[20];
struct hash{
	string type;
	string id;
	char scope;
	int line;
	int size;
	};
ptr head;
ptr curr;
ptr temp;
int k=0;
char c[1];
string t,v;
FILE *fp; 
				
void readf();
				
char * get()
{
	kx++;
	return mat[kx];
}
									
void build()
{
if(head==NULL)
{
head=(ptr)malloc(sizeof(struct node));
strcpy(head->type,t.c_str());
strcpy(head->val,v.c_str());

curr=head;
}
else
{
temp=(ptr)malloc(sizeof(struct node));
strcpy(temp->type,t.c_str());
strcpy(temp->val,v.c_str());
curr->next=temp;
temp->prev=curr;
curr=temp;
}
}
int i=0;

void display()
{
curr=head;
while(curr!=NULL)
{
if(strcmp(curr->val,"\n")==0)
{
//cout<<"\n";
}
else
{
//fp1=fopen("checker.txt","w");
strcpy(put,curr->val);
put[strlen(curr->val)]='\0';
i=0;
while(i!=strlen(curr->val))
{
//	putc(put[i],fp1);
	i++;
}
strcpy(mat[india],curr->val);
india ++;
strcpy(str,put);
put[i]='\0';

}
curr=curr->next;
}
strcpy(mat[india],"$");
}

void readf()
{//
fp=fopen("sample_code.c","r");
if(fp==NULL)
c[0]=getc(fp);
while(c[0]!=-1)
{
	if(c[0]=='+'||c[0]=='-'||c[0]=='*')
	{
		sec[0]=c[0];
		sec[1]='\0';
		c[0]=getc(fp);

		if(c[0]!='+' && c[0]!='-')
		{
		t.assign("arithmetic operator");
		v.assign(sec);
		build();
		continue;
		}
		else
		{
		t.assign("arithmetic operator");
		v.assign(sec);
		sc=c[0];
		v.append(&sc);	
		}

	}
	else if(c[0]=='>'||c[0]=='<'||c[0]=='!'||c[0]=='=')
	{
	sec[0]=c[0];
	sec[1]='\0';
	c[0]=getc(fp);
		if(c[0]!='=')
		{
		t.assign("relational operator");
		v.assign(sec);
		build();
		continue;
		}
		else
		{
		t.assign("relational operator");
		v.assign(sec);
		//cout<<sec<<"\n";
		strcpy(str,sec);
		sc=c[0];
		//cout<<sc<<"\n";
		
		strcpy(str,sec);
		v.append(&sc);
		}
	}

	else if(c[0]>='0'&&c[0]<='9')
	{
		char a[1024];
		while(c[0]>='0'&&c[0]<='9')
		{
		a[k]=c[0];
		++k;
		t.assign("number");
		c[0]=getc(fp);
		}
		a[k]='\0';
		v=string(a);
		k=0;
		build();
		continue;	
	}
	else if(c[0]=='{'||c[0]=='}'||c[0]==';'||c[0]==','||c[0]=='('||c[0]==')'|| c[0]==']'|| c[0]=='[')
	{
	t.assign("special");
	v.assign(c);
	}
	else if(c[0]=='\n')
	{
	t.assign("\0");
	v.assign("\n");	
	}
	else if(c[0]=='\"'||c[0]=='\'')
	{
	k=0;
	char a[1024];
	c[0]=getc(fp);
	while(c[0]!='\"'&&c[0]!='\'')
		{
		a[k]=c[0];	
		c[0]=getc(fp);
		++k;	
		}
	a[k]='\0';	
	t.assign("constant string");
	v=string(a);
	k=0;
	c[0]=getc(fp);
	}

	else if((c[0]>='a'&&c[0]<='z')||(c[0]>='A'&&c[0]<='Z')||c[0]=='_')
	{
		char a[1024];
		while((c[0]>='a'&&c[0]<='z')||(c[0]>='A'&&c[0]<='Z')||c[0]=='_'||(c[0]<='9'&&c[0]>='0'))
		{
		a[k]=c[0];
		++k;
		c[0]=getc(fp);
		}
		a[k]='\0';
		v=string(a);
		k=0;
		if(v=="int"||v=="float"||v=="double"||v=="long"||v=="if"||v=="while"||v=="else"||v=="for"||v=="main"||v=="void")
		{
		t.assign("keyword");
		}
		else
		{
		t.assign("identifier");
		}
		build();
		v="\0";
	continue;	
	}
if(v!="\0")
{
build();
}
c[0]=getc(fp);
}
}

/*
int main()
{
	readf();
	display();
	cout<<"No error encountered. Different tokens I encountered are:\n";
	for(int z=0;z<30;z++)
	{
		cout<<mat[z]<<endl;
	}
	return 0;
}
*/
